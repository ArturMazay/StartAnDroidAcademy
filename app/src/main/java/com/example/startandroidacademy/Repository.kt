package com.example.startandroidacademy
import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.startandroidacademy.data.loadMovies
import kotlinx.coroutines.*


class Repository() {

     private val createSuperScope = CoroutineScope(Dispatchers.IO)


     fun updateData() {
        createSuperScope.launch {
            val movieList = loadMovies(requireContext()) //? чтоб засунуть контекс надо юзать дагер хилти итд?

        }
    }

    private val superExceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        Log.e("TAG", "SuperExceptionHandler [canceledContext:$canceledContext]")
        createSuperScope.launch {
            logExceptionSuspend("superExceptionHandler", exception)
        }
    }

    private suspend fun logExceptionSuspend(who: String, throwable: Throwable) =
        withContext(Dispatchers.Main) {
            Log.e("TAG", "$who::Failed", throwable)

        }
}