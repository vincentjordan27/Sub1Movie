package com.example.android.sub1movie.core.data

import com.example.android.sub1movie.core.data.source.local.LocalDataSource
import com.example.android.sub1movie.core.data.source.remote.RemoteDataSource
import com.example.android.sub1movie.core.data.source.remote.network.ApiResponse
import com.example.android.sub1movie.core.data.source.remote.response.MovieResponse
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.repository.IMovieRepository
import com.example.android.sub1movie.core.utils.AppExecutors
import com.example.android.sub1movie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

    override suspend fun getDetailMovie(id: Int): Flow<DetailMovie> {
        return remoteDataSource.getMovieDetail(id).map {
            DataMapper.mapResponseToPresentation(it)
        }

    }

}