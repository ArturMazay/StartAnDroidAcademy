package com.example.startandroidacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListenerToMovieDetails {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, TitleMovieFragment())
            .commit()
    }

    override fun onClickOpenDetailsMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailsMovieFragment())
            .commit()
    }
}