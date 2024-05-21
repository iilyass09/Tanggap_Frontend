package com.example.massive.ui.screen.akun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.example.massive.ui.theme.poppins

@Composable
fun Bahasa(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Text(
                text = "Disarankan",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Indonesia (ID)",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "English (US)",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Lainnya",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "French",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Hindi",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Mandarin",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Spanish",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Filipino",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Afrikaans",
                color = Color.Black,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        }
    }
}