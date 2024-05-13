package com.example.massive.ui.screen.berita

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.data.Berita
import com.example.massive.data.DataBerita
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.poppins

@Composable
fun DetailBerita(
    navController: NavController,
    beritasId : Int?
) {
    val newBeritasList = DataBerita.ListBerita.filter { berita ->
        berita.id == beritasId
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
       item{
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .background(color = Color.White)
           ) {
               DetailBeritaContent(
                   navController = rememberNavController(),
                   newBeritasList = newBeritasList
               )
           }
       }
    }
}

@Composable
private fun DetailBeritaContent(
    navController: NavController,
    newBeritasList : List<Berita>,
    modifier : Modifier = Modifier
) {
    Column(
        Modifier.offset(y = (-50).dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .height(320.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(),
            painter = painterResource(id = newBeritasList[0].foto),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-30).dp)
                .padding(horizontal = 20.dp),
            text = newBeritasList[0].judul,
            textAlign = TextAlign.Start,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-25).dp)
                .padding(horizontal = 20.dp),
            text = newBeritasList[0].waktu,
            textAlign = TextAlign.Start,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Abu
        )
        Text(
            modifier = Modifier
                .offset(y = (-10).dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = newBeritasList[0].uraian,
            textAlign = TextAlign.Start,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        )
    }
}
