package com.example.android.sub1movie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse (
    @field:SerializedName("results")
    val results: List<MovieResponse>
)