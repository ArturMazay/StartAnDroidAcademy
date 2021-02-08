package com.example.startandroidacademy.repository

import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Genre
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.database.MovieDataBase
import com.example.startandroidacademy.network.*


class Repository(private val moviesApi: MoviesApi, private val dataBase: MovieDataBase) :
    SafeApiRequest() {


    private suspend fun loadMovieAdnGenreFromBd() = dataBase.movieDao.getMoviesWithGenres()

    private suspend fun loadMovieGenreAndActorFromBd(movieID: Long) =
        dataBase.movieDao.getMovieWithGenresAndActors(movieID)


    private suspend fun loadGenres() = apiRequest { moviesApi.getGenres() }

    private suspend fun loadPopularMovies(page: Int) =
        apiRequest { moviesApi.getPopularMovies(page) }

    private suspend fun loadActors(movieID: Int) =
        apiRequest { moviesApi.getMovieActors(movieID) }

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


