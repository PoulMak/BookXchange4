package com.example.bookxchange.di

import com.example.bookxchange.map.data.MapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMapApi(): MapApi {
        return ApiAdapter.buildMapApi()
    }
}