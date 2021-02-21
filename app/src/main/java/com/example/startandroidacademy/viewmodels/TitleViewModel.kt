package com.example.startandroidacademy.viewmodels


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.startandroidacademy.data.Movie
import com.example.startandroidacademy.repository.Repository
import com.example.startandroidacademy.sealed.*
import com.example.startandroidacademy.utils.Coroutines
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


class TitleViewModel(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {

    @ExperimentalCoroutinesApi
    val query =
        BroadcastChannel<String>(Channel.CONFLATED)     //*поидее поток с данными из репозитори или в корунтину загруженный

    private val _searchState = MutableLiveData<StatusSearch>() //лайфдата со статусом

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val _searchResult = query
        .asFlow()
        .debounce(500)  //Возвращает поток, который отражает исходный поток, но отфильтровывает значения, за которыми следуют более новые значения в течение заданного времени ожидания
        .onEach {
            _searchState.value = Loading         //по идее покажет в логе что ЗАГРУЗИЛОСЬ
        }
        .mapLatest {
            if (it.isEmpty()) {
                EmptyQuery
            } else {
                try {
                    val result = repository.searchMovies(it)   // тут приходят данные с репозитория
                    if (result.isEmpty()) {
                        EmptyResult
                    } else {
                        ValidResult(result)
                    }
                } catch (e: Throwable) {
                    if (e is CancellationException) {
                        throw e
                    } else {
                        Log.w(TitleViewModel::class.java.name, e)
                        ErrorResult(e)
                    }
                }
            }
        }
        .onEach {
            _searchState.value = Ready
        }
        .catch { emit(TerminalError) }
        .asLiveData(viewModelScope.coroutineContext)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val searchResult: LiveData<MoviesResult>
        get() = _searchResult

    val searchState: LiveData<StatusSearch>
        get() = _searchState

// поидее можно записать все покороче(но позже)

/*Широковещает последний отправленный элемент (он же значение ) всем подписчикам openSubscription .
Элементы, отправленные обратно для отправки,
 объединяются - принимается только самое последнее отправленное значение,
 а ранее отправленные элементы теряются .
Каждый подписчик немедленно получает последний отправленный элемент.
Отправитель на этот канал вещания никогда не приостанавливает работу,
 а предложение всегда возвращается true.*/

    private lateinit var job: Job

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies


    private fun getMovies() {
        job = Coroutines.ioToMain(
            { repository.loadMovies(page = 1) },
            { _movies.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

    init {
        getMovies()
    }
}