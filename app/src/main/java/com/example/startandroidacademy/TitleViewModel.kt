package com.example.startandroidacademy


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startandroidacademy.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class TitleViewModel (private val repo:Repository):ViewModel(){



     private val movies = MutableStateFlow<List<Movie>>(listOf())

     init {
         getMovies()
     }

     fun getMovies() {
         viewModelScope.launch {
             movies.value = repo.loadMovies()
         }
     }
 /*   private val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData() //что то не вышло в лайфдату затолкать
    val mutableLifeData: LiveData <MutableList<Movie>>                               //или база хромает опять

    get() = mutableLifeData

    init {
        var listData = mutableListOf<Movie>()
        //listData = repo.loadMovies()
    }*/

}
