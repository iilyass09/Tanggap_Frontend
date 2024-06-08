package com.example.massive.data.models

data class Berita(
    val id: Int,
    val judul : String,
    val uraian : String,
    val foto : Int = 0,
    val waktu : String
)
