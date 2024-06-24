package com.example.massive.presentation.screen.onboarding

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun Onboarding1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 30.dp, top = 40.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Login.route) },
                textAlign = TextAlign.End,
                text = "Lewati",
                color = Biru,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins
            )
            Image(
                painter = painterResource(id = R.drawable.splash1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = 50.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Pengaduan laporkan keluhan\n" + "kamu secara instan",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp,
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Laporkan masalah yang terjadi di sekitar\n" + "kamu",
                color = Color.Black,
                lineHeight = 20.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins
            )
        }
        Image(
            painter = painterResource(id = R.drawable.garis1),
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
                    navController.navigate(Screen.Onboarding2.route)
                }
        )
    }
}