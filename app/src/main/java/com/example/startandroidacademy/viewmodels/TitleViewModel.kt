package com.example.startandroidacademy.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.repository.Repository
import com.example.startandroidacademy.utils.Coroutines
import kotlinx.coroutines.Job


class TitleViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var job: Job

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies


    private fun getMovies() {
        job = Coroutines.ioToMain(
            { repository.loadMovies(page = 1) },
            { _movies.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

    init {
        getMovies()
    }
}