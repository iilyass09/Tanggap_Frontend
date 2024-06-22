package com.example.massive.ui.screen.akun

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.contentColorFor
import coil.compose.rememberImagePainter
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Merah
import com.example.massive.ui.theme.poppins

@Composable
fun PengaduanSaya(
    navController: NavController,
) {
    val context = LocalContext.current
    val aduanViewModel :AduanViewModel = viewModel()
    val aduanList by aduanViewModel.aduanList.observeAsState(emptyList())
    val shouldShowDialog = remember { mutableStateOf(false) }

    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userId = sharedPreferencesManager.userId ?: return
    val token = sharedPreferencesManager.authToken ?: return

    LaunchedEffect(Unit) {
        aduanViewModel.fetchAduanList(userId.toString(), token)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 10.dp)
        ) {
            items(aduanList) { aduan ->
                Surface(
                    color = contentColorFor(backgroundColor = Color.White),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, color = Abu2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(215.dp)
                        .padding(vertical = 5.dp)
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
                                text = aduan.createdAt.substring(0, 10),
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
                                    painter = rememberImagePainter(data = aduan.foto),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.matchParentSize()
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = aduan.judul,
                                    fontFamily = poppins,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = aduan.uraian,
                                    fontFamily = poppins,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            }
                        }
                        Button(
                            onClick = { shouldShowDialog.value = true },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(50.dp),
                            contentPadding = PaddingValues(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(50.dp)
                                    .background(brush = Brush.horizontalGradient(listOf(Merah, Merah))),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Batalkan Pengaduan",
                                    fontSize = 14.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        if (shouldShowDialog.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
        }

        if (shouldShowDialog.value) {
            MyAlertDialog(shouldShowDialog = shouldShowDialog, navController)
        }
    }
}

@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>, navController: NavController) {
    AlertDialog(
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Batalkan Pengaduan?",
                    color = Color.Black,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Abu),
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        onClick = {
                            shouldShowDialog.value = false
                        }
                    ) {
                        Text(
                            text = "Tidak",
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Merah),
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        onClick = {
                            navController.navigate(Screen.Home.route)
                        }
                    ) {
                        Text(
                            text = "Ya",
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    )
}


