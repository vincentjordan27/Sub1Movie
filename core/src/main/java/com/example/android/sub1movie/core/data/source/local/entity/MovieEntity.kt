package com.example.android.sub1movie.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable