package com.example.massive.data.models

data class User(
    val id: Int,
    val nama_depan: String,
    val nama_belakang: String,
    val email: String,
    val password: String
)

data class UserRespone(
    val status: Int,
    val message: String,
    val data: User
)