package com.example.massive.data.api

import com.example.massive.data.models.RegisterResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApi {
    @FormUrlEncoded
    @POST("/api/admin/register")
    suspend fun registerUser(
        @Field("namadepan") namaDepan: String,
        @Field("namabelakang") namaBelakang: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("level") level: String = "member",
        @Field("aktif") aktif: String = "1"
    ): Response<RegisterResponse>
}