package com.example.startandroidacademy.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchMovieresponse


    @GET("movie/popular?")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MoviesResponse>


    @GET("movie/{movie_id}/credits?")
    suspend fun getMovieActors(@Path("movie_id") id: Int): Response<ActorResponse>

    @GET("genre/movie/list?")
    suspend fun getGenres(): Response<GenresResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "f9a69c2690fc50b90e76792d9601d4fb"

        @ExperimentalSerializationApi
        operator fun invoke(): MoviesApi {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

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