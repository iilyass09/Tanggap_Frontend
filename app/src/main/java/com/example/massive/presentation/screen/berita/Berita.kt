package com.example.massive.presentation.screen.berita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.BeritaCard
import com.example.massive.presentation.components.BeritaTerbaruCard
import com.example.massive.presentation.components.ErrorView

@Composable
fun BeritaScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val beritaApi = RetrofitInstance.beritaApi
    val factory = BeritaViewModelFactory(sharedPreferencesManager, beritaApi)
    val viewModel: BeritaViewModel = viewModel(factory = factory)

    val beritaList by viewModel.beritaList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        val token = sharedPreferencesManager.authToken ?: return@LaunchedEffect
        viewModel.fetchBerita(token)
    }

    val beritaTerbaru = remember(beritaList) {
        beritaList.sortedByDescending { it.createdAt }
            .take(3)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (errorMessage != null) {
                ErrorView(errorMessage = errorMessage!!) {
                    val token = sharedPreferencesManager.authToken ?: return@ErrorView
                    viewModel.fetchBerita(token)
                }
            } else {
                val listState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if (beritaTerbaru.isNotEmpty()) {
                        item {
                            Text(
                                text = "Berita Terbaru",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                            )
                        }
                        item {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                items(beritaTerbaru) { berita ->
                                    BeritaTerbaruCard(berita = berita, navController = navController)
                                }
                            }
                        }
                    }
                    item {
                        Text(
                            text = "Berita Lainnya",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                        )
                    }
                    items(beritaList) { berita ->
                        BeritaCard(berita = berita, navController = navController)
                    }
                }
            }
        }
    }
}
