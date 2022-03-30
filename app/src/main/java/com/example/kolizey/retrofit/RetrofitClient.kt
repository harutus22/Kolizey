package com.example.kolizey.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import retrofit2.converter.jackson.JacksonConverterFactory


class RetrofitClient {
    companion object{
        private lateinit var retrofitClient: Retrofit
        private val BASE_URL = "https://api.ittour.com.ua/module/"

        fun getRetrofitClient(): Retrofit {
            if (!this::retrofitClient.isInitialized){
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val client = OkHttpClient.Builder().build()
                retrofitClient = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofitClient
        }
    }
}