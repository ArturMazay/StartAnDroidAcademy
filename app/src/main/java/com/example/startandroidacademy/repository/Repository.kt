package com.example.startandroidacademy.repository

import android.app.Application
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Genre
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.database.GenreEntity
import com.example.startandroidacademy.database.MovieDatabase
import com.example.startandroidacademy.database.MovieWithGenres
import com.example.startandroidacademy.database.MovieWithGenresAndActors
import com.example.startandroidacademy.network.*


class Repository(private val moviesApi: MoviesApi, application: Application) : SafeApiRequest() {

    private val db = MovieDatabase.create(application).movieDao




    private fun mapMovieGenresBd(movieWithGenres: List<MovieWithGenres>,genres: List<GenreEntity>): List<Movie> {

        return movieWithGenres.map {
            Movie(id=it.movie.id.toInt(),
                title=it.movie.title,
                overview = it.movie.overview,
                backdrop = it.movie.backdrop,
                poster = it.movie.poster,
                adult = it.movie.adult,
                voteAverage = it.movie.voteAverage,
                voteCount = it.movie.voteCount,
                genres = genres.map { genreEntity ->
                    Genre(
                        id = genreEntity.genreId.toInt(),
                        name = genreEntity.name
                    )
                }

            )
        }
    }



    fun loadActorGenresFromBa(movieID: Int): List<Actor> {
        val data = db.getMovieWithGenresAndActors(movieID.toLong())
        return mapActor(data)
    }

    private fun mapActor(
        actor: MovieWithGenresAndActors
    ): List<Actor> {

        return actor.actors.map {
            Actor(
                id = it.actorId,
                name = it.name,
                picture = it.picture
            )
        }
    }


    private fun loadGenreFromBd(): List<Genre> {
        val data = db.getMoviesWithGenres()
        return mapGenre(data)
    }

    private fun mapGenre(
        genres: MovieWithGenres
    ): List<Genre> {
        return genres.genres.map {
            Genre(
                id = it.genreId.toInt(),
                name = it.name
            )
        }
    }


    private suspend fun loadGenres() = apiRequest { moviesApi.getGenres() }

    private suspend fun loadPopularMovies(page: Int) =
        apiRequest { moviesApi.getPopularMovies(page) }

    private suspend fun loadActors(movieID: Int) = apiRequest { moviesApi.getMovieActors(movieID) }

    private suspend fun loadGenre(): List<Genre> {
        val data = loadGenres()
        return parseGenresListResponse(data)
    }

    suspend fun loadActor(movieID: Int): List<Actor> {
        val data = loadActors(movieID)
        return parseActorsListResponse(data)
    }

    suspend fun loadMovies(page: Int): List<Movie> {
        val genresMap = loadGenre()

        val data = loadPopularMovies(page)
        return mapMovies(data, genresMap)

    }

    private fun mapMovies(
        moviesResponse: MoviesResponse,
        genres: List<Genre>
    ): List<Movie> {

        val genresMap = genres.associateBy { it.id }

        return moviesResponse.movies.map { jsonMovie ->
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                overview = jsonMovie.overview,
                poster = getPosterUrl(jsonMovie.backdropPicture),
                backdrop = getBackdropUrl(jsonMovie.posterPicture),
                voteAverage = jsonMovie.voteAverage,
                adult = if (jsonMovie.adult) 16 else 13,
                voteCount = jsonMovie.votesCount,
                genres = jsonMovie.genreIds.map {
                    genresMap[it] ?: throw IllegalArgumentException("Genre not found")
                }
            )
        }
    }

    private fun parseGenresListResponse(
        genresResponse: GenresResponse
    ): List<Genre> {

        return genresResponse.genres.map { jsonGenre ->
            Genre(
                id = jsonGenre.id,
                name = jsonGenre.name
            )
        }
    }

    private fun parseActorsListResponse(
        actorResponse: ActorResponse
    ): List<Actor> {

        return actorResponse.cast.map { jsonActor ->
            Actor(
                id = jsonActor.id,
                name = jsonActor.name,
                picture = getActorPictureUrl(jsonActor.profilePath) ?: ""
            )
        }
    }

    companion object {
        private const val imagesUrl = "https://image.tmdb.org/t/p/"
        private const val backdropPreferWidth = "w780"
        private const val posterPreferWidth = "w500"
        private const val profilePreferWidth = "w185"

        private fun getImageUrl(image: String?, preferWidth: String): String? {
            return if (image != null) imagesUrl + preferWidth + image else null
        }

        fun getBackdropUrl(image: String?) = getImageUrl(image, backdropPreferWidth)
        fun getPosterUrl(image: String?) = getImageUrl(image, posterPreferWidth)
        fun getActorPictureUrl(image: String?) = getImageUrl(image, profilePreferWidth)
    }
}
