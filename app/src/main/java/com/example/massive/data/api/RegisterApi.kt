package com.example.massive.data.api

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
        @Field("nama_depan") namaDepan: String,
        @Field("nama_belakang") namaBelakang: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("level") level: String, // enum 'admin' or 'member'
        @Field("aktif") aktif: String  // '0' or '1'
    ): Response<RegisterResponse>
}

data class RegisterResponse(
    val success: Boolean,
    val message: String
)
