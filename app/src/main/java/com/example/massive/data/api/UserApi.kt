package com.example.massive.data.api

import android.content.Context
import com.example.massive.data.models.UserRespone
import com.example.massive.data.storage.SharedPreferencesManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/edit/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserRespone
}