package com.example.massive.presentation.screen.fpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.R
import com.example.massive.presentation.components.ButtonPerbarui
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.poppins

@Composable
fun FPassword4(navController: NavController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Berhasil",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-50).dp)
                    .padding(top = 10.dp),
                text = "Selamat! Kata sandi Anda telah diubah.\n" +
                        "Klik lanjutkan untuk login",
                color = Abu2,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))
            ButtonPerbarui(
                stringResource(id = R.string.masuk)
            )
        }
    }
}

@Preview
@Composable
fun FPassword4Prev() {
    FPassword4(navController = rememberNavController())
}