package com.example.massive.data.api

import android.content.Context
import com.example.massive.data.models.UpdateUser
import com.example.massive.data.models.User
import com.example.massive.data.models.UserRespone
import com.example.massive.data.models.UserUpdateRespone
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path

interface UserApi {
    @GET("users/edit/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserRespone

    @FormUrlEncoded
    @POST("users/update/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Field("namadepan") namadepan: String,
        @Field("namabelakang") namabelakang: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: String,
        @Field("aktif") aktif: Char
    ): UserUpdateRespone
}