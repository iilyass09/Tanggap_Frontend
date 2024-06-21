package com.example.massive.data.storage

import com.example.massive.data.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val CHATBOT_URL = "https://flask-docker.1i9m1x34nm5m.us-east.codeengine.appdomain.cloud/"
    private const val BACKEND_URL = "http://202.10.41.84:5000"

    //Login
    val loginApi: LoginApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    //Register
    val registerApi: RegisterApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterApi::class.java)
    }

    //Chatbot
    val chatbotApi: ChatbotApi by lazy {
        Retrofit.Builder()
            .baseUrl(CHATBOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatbotApi::class.java)
    }

    //Berita
    val beritaApi: BeritaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaApi::class.java)
    }

    //Pengaduan
    val pengaduanApi: PengaduanApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PengaduanApi::class.java)
    }

    // Aduan
    val aduanApi: AduanApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AduanApi::class.java)
    }
}
