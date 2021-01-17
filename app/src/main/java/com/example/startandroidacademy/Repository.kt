package com.example.startandroidacademy

import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Genre
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.network.ActorResponse
import com.example.startandroidacademy.network.GenresResponse
import com.example.startandroidacademy.network.MoviesApi
import com.example.startandroidacademy.network.MoviesResponse


class Repository(private val moviesApi: MoviesApi) {

    suspend fun loadGenres() = moviesApi.getGenres()

    suspend fun loadPopularMovies(page: Int = 1) = moviesApi.getPopularMovies(page)


    suspend fun loadMovieDetails(movieId: Int) = moviesApi.getMovieById(movieId)


    suspend fun loadActors(movieId: Int) = moviesApi.getMovieActors(movieId)

    internal fun loadMovies(
        moviesResponse: MoviesResponse,
        genresResponse: GenresResponse,
        actorResponse: ActorResponse // вот тут вставляю параметры(результаты запросов с апи) и маплю , правильно же?
    ): List<Movie> {

        val genresMap = parseGenresListResponse(genresResponse)
        val actorsMap = parseActorsListResponse(actorResponse)

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
                    genresMap[it]
                },
                actors = jsonMovie.actors.map {
                    actorsMap[it]
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