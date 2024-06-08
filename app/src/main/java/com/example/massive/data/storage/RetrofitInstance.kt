package com.example.massive.data.storage

import com.example.massive.data.api.ChatbotApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val CHATBOT_URL = "https://chatbot-tanggap-c9421ff1fead.herokuapp.com/"

    //API CHATBOT
    val api: ChatbotApi by lazy {
        Retrofit.Builder()
            .baseUrl(CHATBOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatbotApi::class.java)
    }

    //API BERITA
}
