package com.example.massive.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.massive.R
import com.example.massive.data.models.Berita
import com.example.massive.data.storage.RetrofitInstance.beritaApi
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.screen.berita.BeritaViewModel
import com.example.massive.ui.screen.berita.BeritaViewModelFactory
import com.example.massive.ui.screen.berita.ErrorView
import com.example.massive.ui.screen.community.AduanCard
import com.example.massive.ui.screen.community.CommunityViewModel
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val name = sharedPreferencesManager.name ?: ""
    val factory = BeritaViewModelFactory(sharedPreferencesManager, beritaApi)
    val viewModel: BeritaViewModel = viewModel(factory = factory)
    val beritaList by viewModel.beritaList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val viewModel3: CommunityViewModel = viewModel()

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
                HomeTopBar(name = name)
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
                                BeritaTerbaruHome(berita = berita, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(name : String) {
    TopAppBar(
        title = {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins
            )
        },
        navigationIcon = {
            Box{
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(7.dp)
                        .size(40.dp)
                        .clickable { }
                )
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Image(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "Notif",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(23.dp)
                )
            }
        }
    )
}

@Composable
fun Banner() {
    Column {
        Surface(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 5.dp)
                .fillMaxWidth(),
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            color = Biru
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Biru)
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        text = "Sampaikan suara Anda\n" +
                                "pada kami.",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        text = "Laporan Anda membawa perbaikan.Mari\n" +
                                "bersama wujudkan perubahan yang\n" +
                                "lebih baik untuk masyarakat.",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppins,
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Start
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.iconcardhome),
                        contentDescription = null,
                        modifier = Modifier
                            .offset(x = 118.dp)
                            .scale(1.4f)
                    )
                }
            }
        }
        Text(
            modifier = Modifier.offset(x = 10.dp),
            text = "Komunitas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppins
        )
        Text(
            modifier = Modifier.offset(x = 10.dp, y = 3.dp),
            text = "Temukan pengaduan yang sedang\n" +
                    "ramai dibicarakan",
            color = Abu,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppins
        )
    }
}

@Composable
fun BeritaTerbaruHome(berita: Berita, navController: NavController) {
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

