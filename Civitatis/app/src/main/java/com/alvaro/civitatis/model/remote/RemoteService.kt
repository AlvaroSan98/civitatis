package com.alvaro.civitatis.model.remote

import com.alvaro.civitatis.model.remote.data.RemoteResult
import com.alvaro.civitatis.util.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash()
    ): Response<RemoteResult>

    @GET("/v1/public/characters")
    suspend fun fetchCharactersOrdered(
        @Query("orderBy") orderBy: String,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash()
    ): Response<RemoteResult>

}