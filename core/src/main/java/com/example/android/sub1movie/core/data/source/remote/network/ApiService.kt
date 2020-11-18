package com.example.android.sub1movie.core.data.source.remote.network

import com.example.android.sub1movie.core.data.source.remote.response.detail.DetailMovieResponse
import com.example.android.sub1movie.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

   @GET("now_playing?api_key=f5ed401dc9fbb006d3357deb008e8bff")
   suspend fun getMovies() : ListMovieResponse

   @GET("{id}?api_key=f5ed401dc9fbb006d3357deb008e8bff")
   suspend fun getDetailMovie(@Path("id") id : Int): DetailMovieResponse

}