package com.example.startandroidacademy.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.startandroidacademy.repository.Repository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TitleViewModel(repository) as T
    }

}