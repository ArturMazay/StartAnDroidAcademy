package com.example.startandroidacademy.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.R
import com.example.startandroidacademy.`interface`.OnClickListenerToMovieDetails
import com.example.startandroidacademy.adapters.MovieAdapter
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.network.MoviesApi
import com.example.startandroidacademy.repository.Repository
import com.example.startandroidacademy.viewmodels.MoviesViewModelFactory
import com.example.startandroidacademy.viewmodels.TitleViewModel
import kotlinx.serialization.ExperimentalSerializationApi


class TitleMovieFragment : Fragment() {
    private lateinit var factory: MoviesViewModelFactory
    private var onClickListenerToMovieDetails: OnClickListenerToMovieDetails? = null

    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: TitleViewModel

    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title_movie, container, false)

        val api = MoviesApi()
        val repo = Repository(api)
        factory = MoviesViewModelFactory(repo)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(TitleViewModel::class.java)

        adapter = MovieAdapter(
            onClickListenerToMovieDetails = object
                : OnClickListenerToMovieDetails {
                override fun onClickOpenDetailsMovieFragment(movie: Movie) {
                    onClickListenerToMovieDetails?.onClickOpenDetailsMovieFragment(movie)
                }
            }
        )

        val rv: RecyclerView? = view.findViewById(R.id.rv_movie)
        rv?.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

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



