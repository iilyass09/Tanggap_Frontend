package com.example.massive.ui.screen.akun

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.contentColorFor
import com.example.massive.R
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun PengaduanSaya(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 28.dp, vertical = 10.dp)
        ) {
            Surface(
                color = contentColorFor(backgroundColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, color = Abu2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(215.dp)
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.HourglassTop,
                            contentDescription = null,
                            tint = Biru
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Pengaduan dalam proses",
                            fontFamily = poppins,
                            fontSize = 12.sp,
                            color = Biru
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "05 Mei 2024",
                            fontFamily = poppins,
                            fontSize = 10.sp,
                            color = Abu
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clipToBounds()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.buktijuno),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.matchParentSize()
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column{
                            Text(
                                text = "Buang Sampah Sembarangan",
                                fontFamily = poppins,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Banyak orang buang sampah sembarangan, " +
                                        "tolong pemerintah setempat segera buat " +
                                        "jalan keluar untuk masalah ini",
                                fontFamily = poppins,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = {  },
                            shape = MaterialTheme.shapes.small,
                            border = BorderStroke(1.dp, Biru),
                            modifier = Modifier
                                .width(155.dp)
                                .height(70.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Batalkan Pengaduan",
                                fontSize = 10.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                color = Biru,
                            )
                        }
                        Button(
                            onClick = { navController.navigate(Screen.DetailPengaduanSaya.route) },
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(Biru),
                            modifier = Modifier
                                .width(155.dp)
                                .height(70.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Tinjau Pengaduan",
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PenGsayaPrev() {
    PengaduanSaya(navController = rememberNavController())
}