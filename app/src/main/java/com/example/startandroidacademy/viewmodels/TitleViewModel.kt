package com.example.startandroidacademy.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class TitleViewModel(private val repository: Repository) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Coroutine exception, scope active", throwable)
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private fun getMovies() {
        viewModelScope.launch(exceptionHandler) {
            _movies.value =
                repository.loadMovies(page = 1)

        }
    }

   init {
        getMovies()
    }
}