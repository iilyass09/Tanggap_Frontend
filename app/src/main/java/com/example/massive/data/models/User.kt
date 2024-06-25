package com.example.massive.data.models

data class User(
    val id: Int,
    val nama_depan: String,
    val nama_belakang: String,
    val email: String,
    val password: String,
    val role : String,
    val aktif : Char
)

data class UpdateUser(
    val id: Int,
    val namadepan: String,
    val namabelakang: String,
    val email: String,
    val password: String,
    val role : String,
    val aktif : Char
)

data class UserRespone(
    val status: Int,
    val message: String,
    val data: User
)

data class UserUpdateRespone(
    val status: Int,
    val message: String,
    val data: UpdateUser
)