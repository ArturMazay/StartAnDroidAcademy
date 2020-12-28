package com.example.startandroidacademy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TitleViewModel(repository) as T
    }

}