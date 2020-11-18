package com.example.android.sub1movie.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.usecase.MovieUseCase

class HomeViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {


    fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getAllMovie().asLiveData()
    }
}