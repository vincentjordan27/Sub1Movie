package com.example.android.sub1movie.core.utils

import com.example.android.sub1movie.core.data.source.local.entity.MovieEntity
import com.example.android.sub1movie.core.data.source.remote.response.MovieResponse
import com.example.android.sub1movie.core.data.source.remote.response.detail.DetailMovieResponse
import com.example.android.sub1movie.core.domain.model.DetailMovie
import com.example.android.sub1movie.core.domain.model.Movie


object DataMapper {
    fun mapResponseToEntities(input : List<MovieResponse>) : List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                rating = it.rating,
                title = it.title,
                image = "https://image.tmdb.org/t/p/w500${it.image}"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                id = it.id,
                rating = it.rating,
                title = it.title,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        rating = input.rating,
        title = input.title,
        image = input.image,
        isFavorite = input.isFavorite
    )

    fun mapResponseToPresentation(input: DetailMovieResponse) = DetailMovie(
        overview = input.overview,
        genres = input.genres
    )
}