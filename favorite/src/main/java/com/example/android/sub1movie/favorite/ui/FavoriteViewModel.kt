package com.example.android.sub1movie.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.usecase.MovieUseCase

class FavoriteViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getAllFavorites() : LiveData<List<Movie>>{
        return movieUseCase.getFavoriteMovie().asLiveData()
    }
}