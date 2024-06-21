package com.example.massive.data.api

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @FormUrlEncoded
    @POST("/api/admin/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: Data
)

data class Data(
    val token: String,
    val id : Int
)
