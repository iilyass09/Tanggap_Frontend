package com.example.massive.data.models

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