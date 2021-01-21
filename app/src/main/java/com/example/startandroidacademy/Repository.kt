package com.example.startandroidacademy

import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Genre
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.network.ActorResponse
import com.example.startandroidacademy.network.GenresResponse
import com.example.startandroidacademy.network.MoviesApi
import com.example.startandroidacademy.network.MoviesResponse


class Repository(private val moviesApi: MoviesApi) {

    private suspend fun loadGenres() = moviesApi.getGenres()

    private suspend fun loadPopularMovies(page: Int) = moviesApi.getPopularMovies(page)

    private suspend fun loadActors(movieID: Int) = moviesApi.getMovieActors(movieID)


    private suspend fun loadGenre(): List<Genre> {
        val data = loadGenres()
        return parseGenresListResponse(data)
    }

    private suspend fun loadActor(movieID: Int): List<Actor> {
        val data = loadActors(movieID)
        return parseActorsListResponse(data)
    }

    suspend fun loadMovies(page: Int): List<Movie> {
        val genresMap = loadGenre()
        //val actorsMap = loadActor()

        val data = loadPopularMovies(page)
        return mapMovies(data, genresMap)

    }

    private fun mapMovies(
        moviesResponse: MoviesResponse,
        genres: List<Genre>
    ): List<Movie> {

        val genresMap = genres.associateBy { it.id }
        //val actorsMap = actors.associateBy { it.id }


        return moviesResponse.movies.map { jsonMovie ->
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                overview = jsonMovie.overview,
                poster = jsonMovie.posterPicture,
                backdrop = jsonMovie.backdropPicture,
                ratings = jsonMovie.ratings,
                numberOfRatings = jsonMovie.votesCount,
                minimumAge = if (jsonMovie.adult) 16 else 13,
                runtime = jsonMovie.runtime,
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

        return actorResponse.actors.map { jsonActor ->
            Actor(
                id = jsonActor.id,
                name = jsonActor.name,
                picture = jsonActor.profilePath ?: ""
            )
        }
    }

}