package com.example.android.sub1movie

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.example.android.sub1movie.core.di.databaseModule
import com.example.android.sub1movie.core.di.networkModule
import com.example.android.sub1movie.core.di.repositoryModule
import com.example.android.sub1movie.di.useCaseModule
import com.example.android.sub1movie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}