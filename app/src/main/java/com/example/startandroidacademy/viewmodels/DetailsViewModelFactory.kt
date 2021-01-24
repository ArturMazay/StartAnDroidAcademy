package com.example.startandroidacademy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.startandroidacademy.repository.Repository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repository: Repository, private val movieId: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository, movieId) as T
    }

}