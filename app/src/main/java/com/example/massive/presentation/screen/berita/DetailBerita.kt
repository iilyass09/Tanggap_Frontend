package com.example.massive.presentation.screen.berita

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.massive.data.models.BeritaDetail
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.ErrorView
import com.example.massive.ui.theme.poppins

@Composable
fun BeritaDetailScreen(beritaId: String) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val beritaApi = remember { RetrofitInstance.beritaApi }
    var beritaDetail by remember { mutableStateOf<BeritaDetail?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(beritaId) {
        val token = sharedPreferencesManager.authToken ?: return@LaunchedEffect
        try {
            val response = beritaApi.getBeritaDetail("Bearer $token", beritaId)
            if (response.isSuccessful) {
                beritaDetail = response.body()?.data
                errorMessage = null
            } else {
                errorMessage = "Gagal memuat detail berita"
            }
        } catch (e: Exception) {
            errorMessage = "Error: ${e.message}"
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (errorMessage != null) {
                ErrorView(errorMessage = errorMessage!!) {
                }
            } else if (beritaDetail != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    BeritaDetailContent(beritaDetail!!)
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
@Composable
fun BeritaDetailContent(beritaDetail: BeritaDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = beritaDetail.judul,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppins,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = rememberAsyncImagePainter(beritaDetail.foto),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = beritaDetail.isi,
            fontSize = 14.sp,
            fontFamily = poppins
        )
    }
}
