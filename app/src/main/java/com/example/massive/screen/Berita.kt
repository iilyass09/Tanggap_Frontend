package com.example.massive.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.data.Berita
import com.example.massive.data.DataBerita
import com.example.massive.data.DataKomunitas
import com.example.massive.data.Komunitas
import com.example.massive.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.poppins

@Composable
fun BeritaScreen(navController: NavController) {
    val beritas: List<Berita> = DataBerita.ListBerita
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy((-15).dp),
                modifier = Modifier.fillMaxSize().offset(y = (-10).dp)
            ) {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(10.dp),
                        horizontalArrangement = Arrangement.spacedBy((20).dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(beritas.filter { it.id in 1..3 }, key = { it.id }) {
                            BeritaTerbaru(berita = it) { beritaId->
                                navController.navigate(Screen.DetailBerita.route + "/$beritaId")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Text(
                        modifier = Modifier.
                        padding(10.dp),
                        text = "Berita Lainnya",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
                items(beritas, key = { it.id }) {
                    BeritaLainnya(berita = it) { beritaId ->
                        navController.navigate(Screen.DetailBerita.route + "/$beritaId")
                    }
                }
            }
        }
    }
}

@Composable
fun BeritaTerbaru(
    berita: Berita,
    onItemClicked : (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(320.dp, 180.dp)
            .clickable { onItemClicked(berita.id) }
    ) {
        Image(
            painter = painterResource(id = berita.foto),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .drawWithContent {
                    drawContent()
                    drawRect(Color.Black.copy(alpha = 0.5f), blendMode = BlendMode.Darken)
                }
        )
        Column {
            Text(modifier = Modifier.padding(start = 16.dp, top = 60.dp),
                text = berita.waktu,
                color = Color.White,
                fontSize = 10.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = berita.judul,
                color = Color.White,
                fontFamily = poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}


@Composable
fun BeritaLainnya(
    berita: Berita,
    onItemClicked2 : (Int) -> Unit
) {
    if (berita.id in 4..10) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .padding(20.dp)
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked2(berita.id) }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    modifier = Modifier.width(250.dp),
                    text = berita.judul,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    lineHeight = 18.sp
                )
                Text(
                    text = berita.waktu,
                    color = Abu,
                    fontSize = 10.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = berita.foto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        }
    }
}

@Preview
@Composable
fun BeritaPrev() {
    BeritaScreen(navController = rememberNavController())
}