package com.example.massive.model

data class Komunitas(
    val id: Int,
    val nama: String,
    val uraian : String,
    val profil : Int = 0,
    val bukti : Int? = null
)
