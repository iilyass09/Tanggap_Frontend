package com.example.massive.data.models

import com.google.gson.annotations.SerializedName

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