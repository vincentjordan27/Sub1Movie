package com.example.android.sub1movie.core.domain.usecase

import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    suspend fun getMovieDetail(id: Int) : Flow<DetailMovie>
}