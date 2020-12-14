package com.example.startandroidacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.data.loadMovies
import kotlinx.coroutines.*


class TitleMovieFragment : Fragment() {

    private var onClickListenerToMovieDetails: OnClickListenerToMovieDetails? = null

    private var coroutineSupervisorScope = createSuperScope()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_title_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        updateData()
    }

    private fun createSuperScope() = CoroutineScope(Dispatchers.IO)

    private fun updateData() {
        coroutineSupervisorScope.async(){
            adapter.bindMovies(loadMovies(requireContext()))
            withContext(Dispatchers.Main) { adapter.notifyDataSetChanged() }
        }
    }

   /* private val superExceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        Log.e("TAG", "SuperExceptionHandler [canceledContext:$canceledContext]")
        coroutineSupervisorScope.launch {
            logExceptionSuspend("superExceptionHandler", exception)
        }
    }
*/
  /*  private suspend fun logExceptionSuspend(who: String, throwable: Throwable) =
        withContext(Dispatchers.Main) {
            Log.e("TAG", "$who::Failed", throwable)
        }
*/

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


