package com.example.startandroidacademy.sealed

import com.example.startandroidacademy.data.MovieSearch

sealed class StatusSearch
object Loading : StatusSearch()
object Ready : StatusSearch()

sealed class MoviesResult
class ValidResult(val result: List<MovieSearch>) : MoviesResult()
object EmptyResult : MoviesResult()
object EmptyQuery : MoviesResult()
class ErrorResult(val e: Throwable) : MoviesResult()
object TerminalError : MoviesResult()