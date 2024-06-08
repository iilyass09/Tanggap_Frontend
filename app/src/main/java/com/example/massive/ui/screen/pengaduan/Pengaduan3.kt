package com.example.massive.ui.screen.pengaduan

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.repository.DataKomunitas
import com.example.massive.data.models.Komunitas
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.screen.home.KomunitasItem
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pengaduan3(navController: NavController) {
    val currentStep = remember { mutableStateOf(2) }
    val sheetState = rememberModalBottomSheetState()
    val pengaduanBottomSheet = rememberSaveable { mutableStateOf(false) }
    val komunitass: List<Komunitas> = DataKomunitas.ListKomunitas

    //BottomSheet
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(2000.dp)
            .background(color = Color.White)
    ) {
        if (pengaduanBottomSheet.value) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { pengaduanBottomSheet.value = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    Arrangement.Center,
                    Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .offset(y = (-45).dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 40.dp),
                        painter = painterResource(id = R.drawable.popupsukses),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = (-70).dp)
                            .fillMaxWidth(),
                        text = "Pengaduan Berhasil",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = (-65).dp)
                            .fillMaxWidth(),
                        text = "Selamat! Pengaduan kamu telah dikirimkan kepada pemerintah setempat.",
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Abu,
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Home.route) },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .offset(y = (-55).dp)
                            .fillMaxWidth()
                            .heightIn(55.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(55.dp)
                                .background(
                                    brush = Brush.horizontalGradient(listOf(Biru, Biru))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Selesai",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins
                            )
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .offset(y = (-10).dp)
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            StepsProgressBar(
                numberOfSteps = 3,
                currentStep = currentStep.value
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(start = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(85.dp)
            ) {
                Text(
                    text = "Isi Form",
                    fontSize = 14.sp
                )
                Text(
                    text = "Bukti",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier.offset(x = (-7).dp),
                    text = "Pratinjau",
                    fontSize = 14.sp,
                    color = Biru
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-5).dp)
            ) {
                items(komunitass.take(1), key = {it.id}) {
                    KomunitasItem(komunitas = it) { komunitasId -> }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { pengaduanBottomSheet.value = true },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .align(Alignment.End),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(55.dp)
                        .background(brush = Brush.horizontalGradient(listOf(Biru, Biru))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Kirim Pengaduan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}