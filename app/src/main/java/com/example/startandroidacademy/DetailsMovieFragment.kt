package com.example.startandroidacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startandroidacademy.data.Actor
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.data.loadMovies
import com.example.startandroidacademy.data.parseActors
import kotlinx.coroutines.*


class DetailsMovieFragment : Fragment() {

    private fun coroutineScoop() = CoroutineScope(Dispatchers.IO)
    private lateinit var adapter: ActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewBack: Button = view.findViewById(R.id.button_back)
        viewBack.setOnClickListener {
            requireActivity().onBackPressed()

        }


        val adapterActor = ActorAdapter()
        val recyclerView: RecyclerView? = view.findViewById(R.id.list_actor)
        recyclerView?.adapter = adapterActor

        val tvName: TextView = view.findViewById(R.id.tv_name)
        val ivBackgraund: ImageView = view.findViewById(R.id.iv_background)
        val movie = requireArguments().getParcelable<Movie>(MOVIE_KEY)!!
     
        movie.run {
            Glide.with(view.context).load(movie.poster).into(ivBackgraund)
            tvName.text = title
            updateData()
        }
    }

    private fun updateData() {
        coroutineScoop().async {
            adapter.bindActor(parseActors(requireContext()))
            withContext(Dispatchers.Main) { adapter.notifyDataSetChanged() }
        }
    }

        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = DetailsMovieFragment()

    }
}