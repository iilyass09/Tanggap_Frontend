package com.example.massive.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

data class Aduan(
    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("lokasi")
    val lokasi: String,

    @field:SerializedName("uraian")
    val uraian: String,

    @field:SerializedName("foto")
    val foto: String,

    @field:SerializedName("tanggapan")
    val tanggapan: String,

    @field:SerializedName("status")
    val status: String
) {
}

data class PengaduanResponse(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: Aduan
)

interface PengaduanApi {
    @FormUrlEncoded
    @POST("/api/aduan/create")
    suspend fun createAduan(
        @Header("Authorization") token: String,
        @Field("userId") userId: Int,
        @Field("judul") judul: String,
        @Field("lokasi") lokasi: String,
        @Field("uraian") uraian: String,
        @Field("foto") foto: String = "modol",
        @Field("tanggapan") tanggapan: String = "test",
        @Field("status") status: String= "baru"
    ): PengaduanResponse
}
