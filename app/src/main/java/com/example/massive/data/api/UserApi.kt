package com.example.massive.data.api

import android.content.Context
import com.example.massive.data.models.UpdateUser
import com.example.massive.data.models.User
import com.example.massive.data.models.UserRespone
import com.example.massive.data.models.UserUpdateRespone
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path

interface UserApi {
    @GET("api/users/edit/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserRespone

    @POST("api/users/update/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UpdateUser): UserUpdateRespone
}