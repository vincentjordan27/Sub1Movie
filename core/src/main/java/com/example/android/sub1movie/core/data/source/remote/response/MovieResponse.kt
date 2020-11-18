package com.example.android.sub1movie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("vote_average")
    val rating: Double,

    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("poster_path")
    val image: String
)