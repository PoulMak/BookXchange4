package com.example.bookxchange.di

import com.example.bookxchange.map.data.MapApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val OSM_BASE_URL = "https://a.tile.openstreetmap.fr/hot/"

class ApiAdapter {
    companion object {
        val mapRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(OSM_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .build()

        fun buildMapApi(): MapApi =
            mapRetrofit.create(MapApi::class.java)
    }
}