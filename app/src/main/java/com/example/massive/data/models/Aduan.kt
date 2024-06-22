package com.example.massive.data.models

data class AduanResponse(
    val status: Int,
    val message: String,
    val data: List<AduanSaya>
)

data class AduanSaya(
    val id: Int,
    val userId: Int,
    val judul: String,
    val lokasi: String,
    val uraian: String,
    val foto: String,
    val status: String,
    val tanggapan: String?,
    val createdAt: String,
    val updatedAt: String,
    val User: Akun
)

data class Akun(
    val id: Int,
    val nama_depan: String,
    val nama_belakang: String,
    val email: String,
    val aktif: String,
    val level: String
)
