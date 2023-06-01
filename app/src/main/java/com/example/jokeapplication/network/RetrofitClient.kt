package com.example.jokeapplication.network

import com.example.jokeapplication.Constant
import com.example.jokeapplication.api.JokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private val retrofit : Retrofit = Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jokeApi: JokeApi get() = retrofit.create(JokeApi::class.java)
    }

}