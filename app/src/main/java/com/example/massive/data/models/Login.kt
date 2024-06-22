package com.example.massive.data.models

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: Data
)

data class Data(
    val token: String,
    val id : Int
)