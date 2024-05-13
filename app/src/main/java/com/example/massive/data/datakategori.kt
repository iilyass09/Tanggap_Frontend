package com.example.massive.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddRoad
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CastForEducation
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Masks
import androidx.compose.material.icons.filled.Money
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.massive.R
import com.example.massive.model.Kategori

object DataKategori {
    val ListKategori = listOf(
        Kategori(
            id = 1,
            nama = "Kesehatan",
            foto = Icons.Filled.HealthAndSafety
        ),
        Kategori(
            id = 2,
            nama = "Pendidikan",
            foto = Icons.Filled.CastForEducation
        ),
        Kategori(
            id = 3,
            nama = "Administrasi",
            foto = Icons.Filled.Book
        ),
        Kategori(
            id = 4,
            nama = "Pertanian",
            foto = Icons.Filled.Agriculture
        ),
        Kategori(
            id = 5,
            nama = "Infrastruktur",
            foto = Icons.Filled.AddRoad
        ),
        Kategori(
            id = 6,
            nama = "Korupsi",
            foto = Icons.Filled.Money
        ),
        Kategori(
            id = 7,
            nama = "Kriminal",
            foto = Icons.Filled.Masks
        ),
        Kategori(
            id = 8,
            nama = "Lainnya",
            foto = Icons.Filled.HealthAndSafety
        ),
    )
}
