package com.example.android.sub1movie.core.data.source.remote

import android.util.Log
import com.example.android.sub1movie.core.data.source.remote.network.ApiResponse
import com.example.android.sub1movie.core.data.source.remote.network.ApiService
import com.example.android.sub1movie.core.data.source.remote.response.MovieResponse
import com.example.android.sub1movie.core.data.source.remote.response.detail.DetailMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource  constructor(private val apiService: ApiService){

    suspend fun getAllMovies() : Flow<ApiResponse<List<MovieResponse>>>{
        return flow {
            try {
                val response = apiService.getMovies()
                val data = response.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getMovieDetail(id: Int) : Flow<DetailMovieResponse> {
        return flow {
            try {
                val response = apiService.getDetailMovie(id)
                emit(response)
            } catch (e: Exception){
                Log.d("GetMovieDetail", e.toString())
            }


        }.flowOn(Dispatchers.IO)
    }

}