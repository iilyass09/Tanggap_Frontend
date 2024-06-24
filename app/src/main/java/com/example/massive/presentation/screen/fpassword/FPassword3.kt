package com.example.massive.presentation.screen.fpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.presentation.components.TextField
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun FPassword3(navController: NavController) {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column {
            Text(
                text = "Tetapkan Kata Sandi Baru",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Buat kata sandi baru. Pastikan itu berbeda dari\n" +
                        "yang sebelumnya untuk keamanan.",
                color = Abu,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(labelValue = "Masukan Kata Sandi Baru Anda")

            Spacer(modifier = Modifier.height(10.dp))
            TextField(labelValue = "Konfirmasi Kata Sandi Baru Anda")

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Biru, Biru))
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Perbarui Kata Sandi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun FPassword3Prev() {
    FPassword3(navController = rememberNavController())
}