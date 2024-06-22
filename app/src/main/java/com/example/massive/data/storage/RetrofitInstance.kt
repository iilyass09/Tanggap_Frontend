package com.example.massive.data.storage

import com.example.massive.data.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val CHATBOT_URL = "https://flask-docker.1i9m1x34nm5m.us-east.codeengine.appdomain.cloud/"
    const val BACKEND_URL = "http://202.10.41.84:5000"

    //POST Login
    val loginApi: LoginApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    //POST Register
    val registerApi: RegisterApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterApi::class.java)
    }

    //POST Chatbot
    val chatbotApi: ChatbotApi by lazy {
        Retrofit.Builder()
            .baseUrl(CHATBOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatbotApi::class.java)
    }

    //GET Berita
    val beritaApi: BeritaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaApi::class.java)
    }

    //POST Pengaduan
    val pengaduanApi: PengaduanApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PengaduanApi::class.java)
    }

    //GET Pengaduan Saya
    val api: PengaduanSayaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PengaduanSayaApi::class.java)
    }
}
