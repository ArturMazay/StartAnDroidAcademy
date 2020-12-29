package com.example.startandroidacademy


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import kotlinx.coroutines.launch


class TitleViewModel(context: Context) : ViewModel() {

    private val repo = Repository(context)
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

     private fun getMovies() {
        viewModelScope.launch {
            _movies.value = repo.loadMovies()
        }
    }

    init {
        getMovies()
    }
}
