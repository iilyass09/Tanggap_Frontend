package com.example.massive.ui.screen.akun

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.poppins

@Composable
fun Bantuan(navController: NavController) {
    var expandedState1 by remember{ mutableStateOf(false) }
    var expandedState2 by remember{ mutableStateOf(false) }
    var expandedState3 by remember{ mutableStateOf(false) }
    var expandedState4 by remember{ mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expandedState1&&expandedState2&&expandedState3&&expandedState4) 90f else 0f
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, color = Abu2),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            delayMillis = 100,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                onClick = { expandedState1 = !expandedState1 }
            ) {
                Column (
                    modifier = Modifier.background(Color.White)
                ){
                    Row(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bagaimana cara melakukan\n" + "pengaduan?",
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.NavigateNext, contentDescription = null,
                            modifier = Modifier
                                .rotate(rotation)
                                .clickable { expandedState1 = !expandedState1 }
                        )
                    }
                    if (expandedState1){
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "1. User mengisi form yang terdapat pada fitur pengaduan (dari topik pengaduan dan uraian mengenai pengaduan\n" +
                                    "2. User dapat menambahkan bukti foto (opsional)\n" +
                                    "3. User bisa melihat pratinjau dari pengaduan yang diajukan.\n" +
                                    "4. Kirim pengaduan dan selesai menunggu proses.\n",
                            fontFamily = poppins,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            maxLines = 7
                            )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, color = Abu2),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            delayMillis = 100,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                onClick = { expandedState2 = !expandedState2 }
            ) {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Apa itu Chatbot?",
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.NavigateNext, contentDescription = null,
                            modifier = Modifier
                                .rotate(rotation)
                                .clickable { expandedState2 = !expandedState2 }
                        )
                    }
                    if (expandedState2){
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "Chatbot adalah program kecerdasan buatan (AI) yang  mampu menirukan " +
                                    "percakapan manusia dalam bentuk pesan teks atau suara melalui aplikasi " +
                                    "pesan, website, atau aplikasi mobile.Selain itu, Bot juga menjadi sarana " +
                                    "dalam memberikan customer support dan merespon pertanyaan pelanggan dengan cepat.",
                            fontFamily = poppins,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            maxLines = 7
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, color = Abu2),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            delayMillis = 100,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                onClick = { expandedState3 = !expandedState3 }
            ) {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bagaimana melihat perkembangan\n" + "proses Pengaduan saya?",
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.NavigateNext, contentDescription = null,
                            modifier = Modifier
                                .rotate(rotation)
                                .clickable { expandedState3 = !expandedState3 }
                        )
                    }
                    if (expandedState3){
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "1. Buka halaman Akun\n" +
                                    "2. Buka menu Pengaduan Saya\n"+
                                    "3. Klik tombol Tinjau Pengaduan\n"+
                                    "4. Perkembangan proses pengaduan sudah terlihat",
                            fontFamily = poppins,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            maxLines = 7
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, color = Abu2),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            delayMillis = 100,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                onClick = { expandedState4 = !expandedState4 }
            ) {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Apa saja yang bisa saya lakukan\n" + "didalam fitur Komunitas?",
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.NavigateNext, contentDescription = null,
                            modifier = Modifier
                                .rotate(rotation)
                                .clickable { expandedState4 = !expandedState4 }
                        )
                    }
                    if (expandedState4){
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "Didalam fitur Komunitas anda bisa melihat " +
                                    "pengaduan yang sudah dilaporkan oleh orang " +
                                    "lain diberbagai daerah Kota Bandung. " +
                                    "Selain itu, anda bisa memberikan komentar " +
                                    "dipengaduan orang lain yang sudah muncul dihalaman Komunitas",
                            fontFamily = poppins,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            maxLines = 7
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BantuanPrev() {
    Bantuan(navController = rememberNavController())
}