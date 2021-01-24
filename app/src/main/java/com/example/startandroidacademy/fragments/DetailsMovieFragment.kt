package com.example.startandroidacademy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startandroidacademy.R
import com.example.startandroidacademy.adapters.ActorAdapter
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.network.MoviesApi
import com.example.startandroidacademy.repository.Repository
import com.example.startandroidacademy.viewmodels.DetailsViewModel
import com.example.startandroidacademy.viewmodels.DetailsViewModelFactory
import com.example.startandroidacademy.viewmodels.MoviesViewModelFactory
import com.example.startandroidacademy.viewmodels.TitleViewModel
import kotlinx.serialization.ExperimentalSerializationApi

class DetailsMovieFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var adapter: ActorAdapter
    private val recyclerView: RecyclerView? = view?.findViewById(R.id.list_actor)
    private lateinit var factory: DetailsViewModelFactory


    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      val view = inflater.inflate(R.layout.fragment_details_movie, container, false)

        val api = MoviesApi()
        val repo = Repository(api)
        factory = DetailsViewModelFactory(repo,arguments?.getInt(PARAM_MOVIE_ID, 0) ?: 0)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)



        val viewBack: Button = view.findViewById(R.id.button_back)
        viewBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val tvName: TextView = view.findViewById(R.id.tv_name)
        val ivBackground: ImageView = view.findViewById(R.id.iv_background)
        val tag: TextView = view.findViewById(R.id.tv_tag)
        val overview: TextView = view.findViewById(R.id.tv_storyline)

        val movie = requireArguments().getSerializable(MOVIE_KEY) as Movie

        recyclerView?.adapter = adapter
        movie.run {
            Glide.with(view.context).load(movie.poster).into(ivBackground)
            tvName.text = movie.title
            tag.text = movie.genres.joinToString(
                separator = ", ",
                transform = { genreItem -> genreItem.name })
            overview.text = movie.overview

        }

        viewModel.actors.observe(viewLifecycleOwner, {
            adapter.listActor = it
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        const val MOVIE_KEY = "key_movie"
        const val PARAM_MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(movie: Movie) = DetailsMovieFragment().apply {
            arguments = Bundle().apply {
                putSerializable(MOVIE_KEY, movie)
                putSerializable(PARAM_MOVIE_ID, movie.id)
            }
        }
    }
}