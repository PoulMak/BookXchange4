package com.example.bookapp.network

import com.example.bookapp.Booklist
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET ("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): Booklist
}