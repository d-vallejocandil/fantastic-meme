package com.example.fantastic_meme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    suspend fun name(
        @Query("apikey") apikey: String = "559256d3",
        @Query("t") t: String
    ): Response
    @GET("/")
    suspend fun id(
        @Query("apikey") apikey: String = "559256d3",
        @Query("i") i: String
    ): Response
    companion object {
        fun get(): Api {
            return Retrofit.Builder().baseUrl("https://www.omdbapi.com/").addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
        }
    }
}