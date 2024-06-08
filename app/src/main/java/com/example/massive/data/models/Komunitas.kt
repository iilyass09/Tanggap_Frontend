package com.example.massive.data.models

data class Komunitas(
    val id: Int,
    val nama: String,
    val uraian : String,
    val profil : Int = 0,
    val bukti : Int? = null
)
