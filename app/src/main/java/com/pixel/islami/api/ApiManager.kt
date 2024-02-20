package com.pixel.islami.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private var retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://mp3quran.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServices(): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}
