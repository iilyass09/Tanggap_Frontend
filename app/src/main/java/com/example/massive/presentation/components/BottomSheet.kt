package com.example.massive.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.data.storage.UserDataStore
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AkunBottomSheet(
    keluarBottomSheet: MutableState<Boolean>,
    sharedPreferencesManager: SharedPreferencesManager,
    navController: NavController
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val userDataStore = remember { UserDataStore(context) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .background(color = Color.White)
    ) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { keluarBottomSheet.value = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .offset(y = (-45).dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 40.dp),
                    painter = painterResource(id = R.drawable.popupkeluar),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    text = "Apakah anda yakin ingin keluar?",
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        sharedPreferencesManager.clear()
                        coroutineScope.launch {
                            userDataStore.clearStatus()
                        }
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                        keluarBottomSheet.value = false // Tutup bottom sheet setelah keluar
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .offset(y = (-18).dp)
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
                            text = "Keluar",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotBottomSheet(
    keluarChatbotBottomSheet: MutableState<Boolean>,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .background(color = Color.White)
    ) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { keluarChatbotBottomSheet.value = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .offset(y = (-45).dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 40.dp),
                    painter = painterResource(id = R.drawable.popupkeluar),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    text = "Apakah ingin mengakhiri sesi dengan chatbot?",
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        keluarChatbotBottomSheet.value = false
                        navController.navigate(Screen.Home.route)
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .offset(y = (-18).dp)
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
                            text = "Keluar",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengaduanBottomSheet(
    pengaduanBottomSheet: MutableState<Boolean>,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState()

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
}