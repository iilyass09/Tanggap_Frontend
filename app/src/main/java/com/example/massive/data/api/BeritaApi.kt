package com.example.massive.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BeritaApi {
    @GET("/api/admin/berita/get")
    suspend fun getBerita(@Header("Authorization") token: String): Response<BeritaResponse>
}

data class BeritaResponse(
    val status: Int,
    val message: String,
    val data: List<Berita>
)

data class Berita(
    val id: Int,
    val judul: String,
    val isi: String,
    val foto: String,
    val createdAt: String,
    val updatedAt: String
)