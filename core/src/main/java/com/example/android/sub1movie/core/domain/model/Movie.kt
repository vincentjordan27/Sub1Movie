package com.example.android.sub1movie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: String,

    val rating: Double,

    val title: String,

    val image: String,

    var isFavorite: Boolean = false
) : Parcelable