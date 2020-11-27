package com.example.startandroidacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class TitleMovieFragment : Fragment() {

    private var onClickListenerToMovieDetails:OnClickListenerToMovieDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title_movie, container, false)
       /* val button:Button=view.findViewById(R.id.button_open)
        button.setOnClickListener {
            onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment()
            //Log.e("XXX",("WORK GOOD").toString())
        }*/


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
            onClickListenerToMovieDetails = context as? OnClickListenerToMovieDetails
           // Log.e("XXX",(onClickListenerToMovieDetails==null).toString())
    }

    override fun onDetach() {
        super.onDetach()
        onClickListenerToMovieDetails = null
        //Log.e("XXX",(onClickListenerToMovieDetails==null).toString())
    }
    companion object {

    }
}