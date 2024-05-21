package com.example.massive.ui.screen.pengaduan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.example.massive.R
import com.example.massive.ui.theme.poppins

@Composable
fun Panduan(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "1. Dihalaman beranda terdapat 5 menu dibawah, [klik] menu Pengaduan.",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "2. Setelah masuk, isi form pengaduan untuk Judul dan Uraian.",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "3. Seteleh mengisi form, bisa menambahkan bukti foto (opsional).",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah3),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "4. Seteleh itu, Anda bisa melihat pratinjau Pengaduan.",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah4),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "5. Jika ada kesalahan dihalaman sebelumnya, anda bisa [klik] tombol kembali.",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah5),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "6. Jika yakin sudah benar, anda bisa [klik] tombol Kirim Pengaduan.",
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.langkah6),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}