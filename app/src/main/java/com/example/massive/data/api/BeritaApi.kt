package com.example.massive.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BeritaApi {
    @GET("/api/berita/get")
    suspend fun getBerita(@Header("Authorization") token: String): Response<BeritaResponse>

    // Endpoint baru untuk detail berita
    @GET("/api/berita/detail/{id}")
    suspend fun getBeritaDetail(
        @Header("Authorization") token: String,
        @retrofit2.http.Path("id") id: String
    ): Response<BeritaDetailResponse>
}

data class BeritaDetailResponse(
    val status: Int,
    val message: String,
    val data: BeritaDetail
)

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

data class BeritaDetail(
    val id: Int,
    val judul: String,
    val isi: String,
    val foto: String,
    val createdAt: String,
    val updatedAt: String
)