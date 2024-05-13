package com.example.massive.model

data class Berita(
    val id: Int,
    val judul : String,
    val uraian : String,
    val foto : Int = 0,
    val waktu : String
)
