package com.example.massive.data.api

import com.example.massive.data.models.PengaduanResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

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
