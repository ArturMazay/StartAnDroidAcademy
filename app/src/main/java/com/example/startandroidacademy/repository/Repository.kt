package com.example.startandroidacademy.repository

import android.app.Application
import android.util.Log
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Genre
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.data.MovieSearch
import com.example.startandroidacademy.database.ActorEntity
import com.example.startandroidacademy.database.GenreEntity
import com.example.startandroidacademy.database.MovieDatabase
import com.example.startandroidacademy.database.MovieWithGenres
import com.example.startandroidacademy.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext


class Repository(private val moviesApi: MoviesApi, application: Application) : SafeApiRequest() {


    @FlowPreview
    suspend fun searchMovies(query: String, page: Int = 1): List<MovieSearch> {

        return withContext(Dispatchers.IO) {
            flowOf(                                             ////Создает поток, который производит значения из указанных аргументов
                moviesApi.searchMovie(API_KEY, query, page)

            )
        }
            .flowOn(Dispatchers.IO)                   //Изменяет контекст, в котором этот поток выполняется, на данный контекст . Этот оператор является составным и влияет только на предыдущие операторы, не имеющие собственного контекста
            .onEach {
                Log.d(
                    Repository::class.java.name,
                    it.movies.toString()
                )
            }   //Выполняет заданное действие
            .flatMapMerge { it.movies.asFlow() } //Преобразует элементы, излучаемые исходным потоком, применяя преобразование , которое возвращает другой поток, а затем объединяет и сглаживает эти потоки.
            .map {
                MovieSearch(
                    id = it.id,
                    title = it.title,
                    poster = it.poster,    //добавить не збыть обработку фотки
                    backdrop = getBackdropUrl(it.backdrop),
                    adult = it.adult,
                    voteCount = it.voteCount,
                    voteAverage = it.voteAverage,
                    overview = it.overview
                )
            }
            .toList()

    }

    private val db = MovieDatabase.create(application).movieDao


    private fun mapMovie(movieWithGenres: List<MovieWithGenres>): List<Movie> {
        return movieWithGenres.map { movieWithGenres ->
            Movie(
                id = movieWithGenres.movie.id.toInt(),
                title = movieWithGenres.movie.title,
                overview = movieWithGenres.movie.overview,
                poster = movieWithGenres.movie.poster,
                backdrop = movieWithGenres.movie.backdrop,
                voteCount = movieWithGenres.movie.voteCount,
                voteAverage = movieWithGenres.movie.voteAverage,
                genres = mapGenresEntityToDomain(movieWithGenres.genres),
                adult = movieWithGenres.movie.adult
            )
        }
    }


    private fun mapGenresEntityToDomain(
        genres: List<GenreEntity>
    ): List<Genre> {

        return genres.map {
            Genre(
                id = it.genreId.toInt(),
                name = it.name
            )
        }
    }

    private fun mapActorEntityToDomain(
        actors: List<ActorEntity>
    ): List<Actor> {

        return actors.map {
            Actor(
                id = it.actorId.toInt(),
                name = it.name,
                picture = it.picture
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
        private const val API_KEY = "f9a69c2690fc50b90e76792d9601d4fb"

        private fun getImageUrl(image: String?, preferWidth: String): String? {
            return if (image != null) imagesUrl + preferWidth + image else null
        }

        fun getBackdropUrl(image: String?) = getImageUrl(image, backdropPreferWidth)
        fun getPosterUrl(image: String) = getImageUrl(image, posterPreferWidth)
        fun getActorPictureUrl(image: String?) = getImageUrl(image, profilePreferWidth)
    }
}

