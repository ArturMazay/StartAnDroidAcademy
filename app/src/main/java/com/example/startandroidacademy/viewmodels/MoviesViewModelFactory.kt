package com.example.startandroidacademy.viewmodels


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.startandroidacademy.repository.Repository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(
    private val repository: Repository,private val application: Application
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TitleViewModel(repository,application) as T
    }

}