// com/example/massive/data/RetrofitInstance.kt
package com.example.massive.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://chatbot-tanggap-c9421ff1fead.herokuapp.com/"

    val api: ChatbotApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatbotApi::class.java)
    }
}
