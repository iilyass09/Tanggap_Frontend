package com.example.massive.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface AduanApi{
    @GET("/api/aduan/get/baru")
    suspend fun getAduanBaru(): Response
}

data class User(
    @SerializedName("nama_depan") val namaDepan: String?,
    @SerializedName("telp") val telp: String?,
    @SerializedName("level") val level: String?,
    @SerializedName("nama_belakang") val namaBelakang: String?,
    @SerializedName("aktif") val aktif: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("tgllahir") val tgllahir: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("jenkel") val jenkel: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("passtex") val passtex: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("updatedAt") val updatedAt: String?
)

data class DataItem(
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("tanggapan") val tanggapan: String?,
    @SerializedName("User") val user: User?,
    @SerializedName("foto") val foto: String?,
    @SerializedName("lokasi") val lokasi: String?,
    @SerializedName("uraian") val uraian: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("judul") val judul: String?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("updatedAt") val updatedAt: String?
)

data class Response(
    @SerializedName("data") val data: List<DataItem>?,
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Int?
)
