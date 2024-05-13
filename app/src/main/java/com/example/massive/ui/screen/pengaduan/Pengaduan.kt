package com.example.massive.ui.screen.pengaduan

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CastForEducation
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ChipDefaults
import com.example.massive.R
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Biru2
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PengaduanScreen(
    navController: NavController,
) {
    var currentStep = remember { mutableStateOf(0) }
    var selectedCategory by remember { mutableStateOf(0) }
    var sheetState = rememberModalBottomSheetState()
    var pengaduanBottomSheet = rememberSaveable { mutableStateOf(false) }

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

    //Konten
    Scaffold(
        modifier = Modifier
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
                numberOfSteps = 2,
                currentStep = currentStep.value
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                Text(
                    text = "Pilih Kategori",
                    fontSize = 14.sp
                )
                Text(
                    text = "Isi Form",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier
                        .offset(x = 18.dp),
                    text = "Pratinjau",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            when (currentStep.value) {
                0 -> Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Pilih kategori pengaduan anda",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    ClickableTextPanduan(onTextSelected = { navController.navigate(Screen.Panduan.route) })
                    Spacer(modifier = Modifier.height(25.dp))
                    PengaduanContentStep0(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it },
                        navController = rememberNavController()
                    )
                }
                1 -> PengaduanContentStep1(currentStep = currentStep, navController = navController)
                2 -> PengaduanContentStep2(currentStep = currentStep, navController = navController)
            }
            Button(
                onClick = {
                    if (currentStep.value < 2) {
                        currentStep.value++
                    } else {
                        pengaduanBottomSheet.value = true
                    }
                },
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
                        text = "Selanjutnya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}
