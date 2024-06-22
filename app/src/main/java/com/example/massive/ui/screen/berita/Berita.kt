package com.example.massive.ui.screen.berita

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.massive.data.models.Berita
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.poppins

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
                                    BeritaTerbaru(berita = berita, navController = navController)
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
                        BeritaItem(berita = berita, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BeritaItem(berita: Berita, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate("${Screen.BeritaDetail.route}/${berita.id}")
            },
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = berita.judul,
                fontSize = 14.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 15.dp)
            )
            Image(
                painter = rememberAsyncImagePainter(berita.foto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
fun BeritaTerbaru(berita: Berita, navController: NavController) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("${Screen.BeritaDetail.route}/${berita.id}")
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        AsyncImage(
            model = berita.foto,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
        )
    }
}




@Composable
fun ErrorView(errorMessage: String, onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error",
            tint = Color.Red,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Red,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onRetryClicked() },
            modifier = Modifier.padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Coba lagi")
            }
        }
    }
}
