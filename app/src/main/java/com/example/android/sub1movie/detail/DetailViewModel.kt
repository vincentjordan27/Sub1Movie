package com.example.android.sub1movie.detail

import android.util.Log
import androidx.lifecycle.*
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _result = MutableLiveData<DetailMovie>()
    val result: LiveData<DetailMovie>
        get() = _result

    fun getDetailMovie(id: Int){
        viewModelScope.launch {
            movieUseCase.getMovieDetail(id).collect {
                _result.value = it
                Log.d("DEBUGSS" , it.toString())
            }
        }
    }

    fun setFavorite(movie: Movie, state: Boolean){
        return movieUseCase.setFavoriteMovie(movie, state)
    }
}