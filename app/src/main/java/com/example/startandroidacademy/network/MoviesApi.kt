package com.example.startandroidacademy.network

import com.example.startandroidacademy.data.Movie
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int) : MoviesResponse    //тут прилетит с сервера, страница с результатом,которые лягут в МУВИРЕСПОНСЕ,в поля page и в результат в лист jsonmovie так ???

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int): JsonMovie

    @GET("movie/{movie_id}/credits")
    fun getMovieActors(@Path("movie_id") id: Int) : JsonActor //актеры

    @GET("genre/movie/list")
    fun getGenres() : JsonGenre //жанры


    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "f9a69c2690fc50b90e76792d9601d4fb"

        @ExperimentalSerializationApi
        fun create() : MoviesApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            //   Interceptor, чтобы добавить api_key во все запросы в качестве authInterceptor
            val authInterceptor = Interceptor { chain ->
                val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(logger)
                .build()

            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }

            val contentType = "application/json".toMediaType()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory(contentType))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(MoviesApi::class.java)
        }

    }

}