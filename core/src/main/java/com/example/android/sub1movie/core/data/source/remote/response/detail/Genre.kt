package com.example.android.sub1movie.core.data.source.remote.response.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @field:SerializedName("name")
    val name: String
) : Parcelable