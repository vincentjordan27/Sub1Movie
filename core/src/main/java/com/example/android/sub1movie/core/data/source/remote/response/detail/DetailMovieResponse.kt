package com.example.android.sub1movie.core.data.source.remote.response.detail

import android.os.Parcelable
import com.example.android.sub1movie.core.data.source.remote.response.detail.Genre
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMovieResponse (
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("genres")
    val genres: List<Genre>

) : Parcelable