package com.example.android.sub1movie.core.data.source.local

import com.example.android.sub1movie.core.data.source.local.entity.MovieEntity
import com.example.android.sub1movie.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val movieDao: MovieDao){

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean){
        movie.isFavorite = state
        movieDao.updateFavoriteMovie(movie)
    }

}