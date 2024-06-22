package com.example.massive.data.api

import com.example.massive.data.models.BeritaDetailResponse
import com.example.massive.data.models.BeritaResponse
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
