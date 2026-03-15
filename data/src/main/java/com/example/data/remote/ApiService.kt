package com.example.data.remote

import androidx.core.os.BuildCompat
import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.model.list.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//import com.example.data.
interface ApiService {
    @GET("games")
    suspend fun getGamesByGenre(
        @Query("genres") genreSlug: String,
        @Query("key") apiKey: String,
        @Query("page") page: Int = 1
    ): GamesResponse

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): GameDetailsResponse
}
