package com.example.massive.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.massive.data.models.AduanSaya
import com.example.massive.data.models.Berita
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

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
            Text(
                text = "${aduan.User.nama_depan} ${aduan.User.nama_belakang}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = poppins
            )
            Text(
                modifier = Modifier.offset(y = (-3).dp),
                text = "Status : ${aduan.status}",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                fontFamily = poppins
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
                text = "${aduan.createdAt} - (${aduan.lokasi})",
                color = Abu,
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
            )
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

@Composable
fun BeritaCard(berita: Berita, navController: NavController) {
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
fun BeritaTerbaruCard(berita: Berita, navController: NavController) {
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
fun BeritaTerbaruHomeCard(berita: Berita, navController: NavController) {
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
fun ChatBubble(message: String, isUser: Boolean) {
    Row(
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))

    ) {
        Box(
            modifier = Modifier
                .background(if (isUser) Biru else Abu2)
                .padding(10.dp)
        ) {
            Text(
                text = message,
                color = if (isUser) Color.White else Color.Black
            )
        }
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
