package com.example.massive.presentation.screen.akun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.example.massive.ui.theme.poppins

@Composable
fun KebijakanPrivasi() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(
                text = "Kebijakan Privasi",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Terakhir diperbarui: [05/03/2024]\n" +
                        "\n" +
                        "Kami di Quake Alert sangat menghargai privasi pengunjung kami. " +
                        "Dokumen Kebijakan Privasi ini menjelaskan bagaimana informasi pribadi " +
                        "Anda dikumpulkan, digunakan, dan diungkapkan saat Anda mengunjungi [https://www.tanggap.com]",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Pengumpulan Informasi dan Penggunaan",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Kami tidak secara aktif mengumpulkan informasi pribadi dari Anda ketika Anda" +
                        " hanya mengunjungi Situs kami. Namun, seperti banyak situs web lainnya, kami " +
                        "menerima dan menyimpan informasi tertentu secara otomatis melalui penggunaan " +
                        "cookie dan alat pelacakan yang umum digunakan. Informasi yang dikumpulkan dapat mencakup alamat protokol internet (IP), jenis peramban, sistem operasi, tanggal dan waktu kunjungan, serta alamat situs web tempat Anda berasal.\n" +
                        "\n" +
                        "Informasi ini digunakan untuk menganalisis kebiasaan pengguna, mengelola Situs, " +
                        "melacak pergerakan pengguna di sekitar Situs, dan mengumpulkan informasi demografis. " +
                        "Data demografis yang dikumpulkan mungkin digunakan dalam penargetan iklan yang sesuai " +
                        "dengan kepentingan pengguna.",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Pengungkapan kepada Pihak Ketiga",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Kami tidak akan menjual, menyewakan, atau memberikan informasi pribadi" +
                        "Anda kepada pihak ketiga kecuali dengan izin Anda atau jika diwajibkan oleh hukum.",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            )
        }
    }
}