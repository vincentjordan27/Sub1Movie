package com.example.android.sub1movie.core.domain.repository

import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies() : Flow<Resource<List<Movie>>>

    fun getFavoriteTourism() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    suspend fun getDetailMovie(id: Int) : Flow<DetailMovie>

}