package com.example.startandroidacademy


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import kotlinx.coroutines.launch


class TitleViewModel( val repo: Repository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

     fun getMovies() {
        viewModelScope.launch {
            _movies.value = repo.loadMovies()
        }
    }

    init {
        getMovies()
    }
}
