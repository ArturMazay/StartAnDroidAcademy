package com.example.startandroidacademy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository, movieID: Int) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAGY", "Coroutine exception, scope active", throwable)
    }

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors

    private fun loadActors(movieID: Int) {
        viewModelScope.launch(exceptionHandler) {
            _actors.value = repository.loadActor(movieID)
        }
    }

    init {
        loadActors(movieID)
    }
}