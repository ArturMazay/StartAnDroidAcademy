package com.example.startandroidacademy.utils

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

object Coroutines {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Coroutine exception, scope active", throwable)
    }

    fun <T : Any> ioToMain(work: suspend (() -> T?), callback: ((T?) -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            val data = CoroutineScope(Dispatchers.IO).async{
                return@async work()
            }.await()
            callback(data)
        }

}