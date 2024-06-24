package com.example.massive.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.data.storage.RetrofitInstance.beritaApi
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.AduanCard
import com.example.massive.presentation.components.Banner
import com.example.massive.presentation.components.BeritaTerbaruHomeCard
import com.example.massive.presentation.components.ErrorView
import com.example.massive.presentation.components.HomeTopBar
import com.example.massive.presentation.screen.berita.BeritaViewModel
import com.example.massive.presentation.screen.berita.BeritaViewModelFactory
import com.example.massive.presentation.screen.community.CommunityViewModel
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.poppins
import com.example.yourapp.network.AkunSayaViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val factory = BeritaViewModelFactory(sharedPreferencesManager, beritaApi)
    val viewModel: BeritaViewModel = viewModel(factory = factory)
    val beritaList by viewModel.beritaList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val viewModel3: CommunityViewModel = viewModel()
    val userViewModel: AkunSayaViewModel = viewModel()

    LaunchedEffect(Unit) {
        userViewModel.fetchUser(context)
    }

    val beritaTerbaru = remember(beritaList) {
        beritaList.sortedByDescending { it.createdAt }
            .take(3)
    }

    val aduanList = viewModel3.aduanList.take(3)
    println("Jumlah Aduan: ${aduanList.size}")

    LaunchedEffect(Unit) {
        val token = sharedPreferencesManager.authToken ?: return@LaunchedEffect
        viewModel.fetchBerita(token)
    }

    val authToken = sharedPreferencesManager.authToken
    println("Token Otorisasi: $authToken")

    authToken?.let {
        LaunchedEffect(authToken) {
            viewModel3.fetchAduan(authToken)
        }
    } ?: run {
        Text(text = "Token tidak ditemukan", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            ErrorView(errorMessage = errorMessage!!) {
                val token = sharedPreferencesManager.authToken ?: return@ErrorView
                viewModel.fetchBerita(token)
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HomeTopBar(navController)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    if (aduanList.isNotEmpty()) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy((-15).dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                Banner()
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            items(aduanList) { aduan ->
                                AduanCard(aduan)
                            }
                            item {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 30.dp),
                                    text = "Berita Terbaru",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                                    text = "Temukan berita terbaru tentang\n" + "Kota Bandung",
                                    color = Abu,
                                    fontSize = 14.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = poppins
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                            itemsIndexed(beritaTerbaru) { index, berita ->
                                if (index > 0) {
                                    Spacer(modifier = Modifier.height(18.dp))
                                }
                                BeritaTerbaruHomeCard(berita = berita, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

