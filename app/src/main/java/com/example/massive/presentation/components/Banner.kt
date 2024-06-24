package com.example.massive.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.massive.R
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

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
            text = "Pantau",
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