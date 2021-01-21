package com.example.startandroidacademy


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TitleViewModel(private val repository: Repository) : ViewModel() {


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Coroutine exception, scope active", throwable)
    }


    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private fun getMovies() {
        viewModelScope.launch (exceptionHandler) {
            _movies.value =
                repository.loadMovies(page = 1)  // вот тут жду готовый лист мови, но хз что ему туда положить...

        }
    }

    init {
        getMovies()
    }

}