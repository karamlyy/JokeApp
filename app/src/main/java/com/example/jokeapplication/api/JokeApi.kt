package com.example.jokeapplication.api

import com.example.jokeapplication.model.JokeDTO
import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {

    @GET("joke/Any")
    suspend fun getRandomJoke(): JokeDTO

}