package com.example.startandroidacademy

import android.app.Application
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module



class App : Application() {

    private val appModule = module {
        single { Repository(context = get()) }       //вот такая запись делает мой класс репозитория зависмистью/зависимы? и выдергивает/или предоставляет ему контекс из него контекст
        single { MoviesViewModelFactory(context = get()) }     //и во всех моих классах которые добавлены в модуль в конструкторах должен быть контекст?

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
