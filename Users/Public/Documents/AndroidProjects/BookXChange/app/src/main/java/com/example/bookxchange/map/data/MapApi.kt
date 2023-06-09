package com.example.bookxchange.map.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MapApi {
    @GET("{scale}/{col}/{row}.png")
    fun getTileByApi(
        @Path("row") row: Int,
        @Path("col") col: Int,
        @Path("scale") scale: Int
    ): Call<ResponseBody>
}