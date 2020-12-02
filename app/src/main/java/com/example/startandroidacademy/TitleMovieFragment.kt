package com.example.startandroidacademy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class TitleMovieFragment : Fragment() {

    private var onClickListenerToMovieDetails: OnClickListenerToMovieDetails? = null
  
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_title_movie, container, false)
    }
  
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val view: View? = view?.findViewById(R.id.inCard)
        view?.setOnClickListener {
            onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onClickListenerToMovieDetails = context as? OnClickListenerToMovieDetails
    }

    override fun onDetach() {
        super.onDetach()
        onClickListenerToMovieDetails = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TitleMovieFragment()

        }
    }

