package com.example.startandroidacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.startandroidacademy.`interface`.OnClickListenerToMovieDetails
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.fragments.DetailsMovieFragment
import com.example.startandroidacademy.fragments.TitleMovieFragment

class MainActivity : AppCompatActivity(), OnClickListenerToMovieDetails {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
            .add(R.id.main_container, TitleMovieFragment.newInstance())
                .commit()
        }
    }

    override fun onClickOpenDetailsMovieFragment(movie: Movie) {
        supportFragmentManager.beginTransaction() 
            .replace(R.id.main_container, DetailsMovieFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }


}