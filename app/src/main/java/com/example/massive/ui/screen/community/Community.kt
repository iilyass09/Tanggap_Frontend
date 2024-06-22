package com.example.massive.ui.screen.community

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.massive.data.models.AduanSaya
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val viewModel: CommunityViewModel = viewModel()
    val aduanList = viewModel.aduanList
    val authToken = sharedPreferencesManager.authToken

    authToken?.let {
        LaunchedEffect(authToken) {
            viewModel.fetchAduan(authToken)
        }
    } ?: run {
        Text(
            text = "Token tidak ditemukan",
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
    }
    if (aduanList.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholder = {
                        Text("Cari nama pengguna")
                    },
                    shape = RoundedCornerShape(20),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                    )
                )
            }

            items(aduanList.filter { aduan ->
                aduan.User.nama_belakang.contains(searchQuery, ignoreCase = true)
            }) { aduan ->
                AduanCard(aduan = aduan)
            }
        }
    } else {
        Text(
            text = "Tidak ada aduan yang tersedia",
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun AduanCard(aduan: AduanSaya) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${aduan.User.nama_depan} ${aduan.User.nama_belakang}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = poppins
            )
            Text(
                modifier = Modifier.offset(y = (-3).dp),
                text = aduan.createdAt,
                color = Abu,
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.height(5.dp))

            SubcomposeAsyncImage(
                model = aduan.foto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Box(Modifier.matchParentSize()) {
                        Text("Gambar tidak dapat dimuat", Modifier.align(Alignment.Center))
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = aduan.uraian,
                fontSize = 14.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )
        }
    }
}
