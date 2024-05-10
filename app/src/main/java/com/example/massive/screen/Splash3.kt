package com.example.EarthquakeApp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.R
import com.example.massive.navigation.PengaduanNavigation
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun Splash3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(213.dp))
            Image(
                painter = painterResource(id = R.drawable.splash3),
                contentDescription = null,
                modifier = Modifier
                    .scale(2.1f)
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Komunitas dapat melihat\n" + "postingan orang lain",
                color = Color.Black,
                lineHeight = 30.sp,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Ketahui permasalahan yang sedang\n" + "hangat dibicarakan",
                color = Color.Black,
                lineHeight = 20.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins
            )
        }
        Image(
            painter = painterResource(id = R.drawable.garis3),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.BottomCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.nextsplash),
            contentDescription = null,
            modifier = Modifier
                .offset(y = (-30).dp)
                .offset(x = (-30).dp)
                .height(40.dp)
                .width(40.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    PengaduanNavigation.goTologin()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.backsplash),
            contentDescription = null,
            modifier = Modifier
                .offset(y = (-30).dp)
                .offset(x = (30.dp))
                .height(40.dp)
                .width(40.dp)
                .align(Alignment.BottomStart)
                .clickable {
                    PengaduanNavigation.goToSplash2()
                }
        )
    }
}

@Preview
@Composable
fun Splash3Prev() {
    Splash3(navController = rememberNavController())
}