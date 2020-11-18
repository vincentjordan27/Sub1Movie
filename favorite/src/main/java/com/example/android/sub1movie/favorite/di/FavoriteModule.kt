package com.example.android.sub1movie.favorite.di

import com.example.android.sub1movie.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}