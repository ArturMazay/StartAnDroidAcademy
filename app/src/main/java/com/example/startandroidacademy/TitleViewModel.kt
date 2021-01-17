package com.example.startandroidacademy


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.network.ActorResponse
import com.example.startandroidacademy.network.GenresResponse
import com.example.startandroidacademy.network.MoviesResponse
import kotlinx.coroutines.launch


class TitleViewModel(private val repository: Repository) : ViewModel() {

    companion object{
        const val page = 1
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

     private fun getMovies() {
        viewModelScope.launch {
            _movies.value = repository.loadMovies(moviesResponse = MoviesResponse(page),genresResponse = GenresResponse(),actorResponse = ActorResponse() )  //что вот тут жду готовый лист мови, но хз что ему туда положить...
        }
    }

    init {
        getMovies()
    }
}
