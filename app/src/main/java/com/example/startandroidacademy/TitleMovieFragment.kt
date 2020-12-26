package com.example.startandroidacademy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.data.Movie


class TitleMovieFragment : Fragment() {

    private var onClickListenerToMovieDetails: OnClickListenerToMovieDetails? = null

    // private val createSuperScope = CoroutineScope(Dispatchers.IO)
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_title_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

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

       //что то не могу подписаться не пойму чтокосячу или это из за этого  MutableStateFlow<List<Movie>>(listOf())?
        //или впечатление что зависимость не подтянул какую то...
       viewModel.getMovies().observe(viewLifecycleOwner, Observer {
           it.let{
               adapter.data = it
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

    override fun onDestroyView() {
        super.onDestroyView()
        // createSuperScope.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TitleMovieFragment()
    }
}


