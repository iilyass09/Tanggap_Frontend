package com.example.massive.data.api

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

data class Aduan(
    val userId: String,
    val judul: String,
    val lokasi: String,
    val uraian: String,
    val foto: String,
    val tanggapan: String,
    val status: String
)

data class PengaduanResponse(
    val status: Int,
    val message: String,
    val data: Aduan
)

interface PengaduanApi {
    @POST("/api/admin/aduan/create")
    suspend fun createAduan(
        @Header("Authorization") token: String,
        @Body aduan: Aduan
    ): PengaduanResponse
}