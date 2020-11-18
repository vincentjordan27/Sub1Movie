package com.example.android.sub1movie.core.domain.model

import android.os.Parcelable
import com.example.android.sub1movie.core.data.source.remote.response.detail.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMovie (
    val overview: String,

    val genres: List<Genre>

) : Parcelable