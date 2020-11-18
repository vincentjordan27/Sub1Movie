package com.example.android.sub1movie.core.domain

import com.example.android.sub1movie.core.data.Resource
import com.example.android.sub1movie.core.domain.model.Movie
import com.example.android.sub1movie.core.domain.repository.IMovieRepository
import com.example.android.sub1movie.core.domain.usecase.MovieInteractor
import com.example.android.sub1movie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp(){
        movieUseCase = MovieInteractor(movieRepository)
        val dummyMovie = Resource.Success(listOf(Movie("1",9.0,"Dummy","No",false)))
        val dummyList = flow {
            emit(dummyMovie)
        }
        Mockito.`when`(movieRepository.getAllMovies()).thenReturn(dummyList)
    }

    @Test fun `should get data from repo`(){
        movieUseCase.getAllMovie()
        Mockito.verify(movieRepository).getAllMovies()
    }

}