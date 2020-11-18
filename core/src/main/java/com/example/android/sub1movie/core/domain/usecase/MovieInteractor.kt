package com.example.android.sub1movie.core.domain.usecase

import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteTourism()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieRepository.setFavoriteMovie(movie, state)
    }

    override suspend fun getMovieDetail(id: Int) : Flow<DetailMovie> {
        return movieRepository.getDetailMovie(id)
    }
}