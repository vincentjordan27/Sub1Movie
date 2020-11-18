package com.example.android.sub1movie.di

import com.example.android.sub1movie.detail.DetailViewModel
import com.example.android.sub1movie.core.domain.usecase.MovieInteractor
import com.example.android.sub1movie.core.domain.usecase.MovieUseCase
import com.example.android.sub1movie.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase>{MovieInteractor(get())}
}

val viewModelModule = module {
    scope(named("AppModuleViewModel")){
        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}