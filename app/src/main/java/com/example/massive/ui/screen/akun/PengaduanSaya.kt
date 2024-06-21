package com.example.massive.ui.screen.akun

import android.content.Context
import android.util.Log
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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.contentColorFor
import coil.compose.rememberImagePainter
import com.example.massive.R
import com.example.massive.data.api.AduanResponse
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Merah
import com.example.massive.ui.theme.poppins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

object RetrofitInstance {
    private const val BASE_URL = "http://202.10.41.84:5000/api/"

    val api: AduanViewModel.ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AduanViewModel.ApiService::class.java)
    }
}


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

    Log.d("Token", "Token Valid: $token")
    Log.d("User ID", "ID Pengguna Valid: $userId")

    LaunchedEffect(Unit) {
        aduanViewModel.fetchAduanList(userId.toString(), token)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 28.dp, vertical = 10.dp)
        ) {
            aduanList.forEach { aduan ->
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
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { shouldShowDialog.value = true },
                                shape = MaterialTheme.shapes.small,
                                colors = ButtonDefaults.buttonColors(Merah),
                                modifier = Modifier
                                    .width(155.dp)
                                    .height(70.dp)
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Batalkan Pengaduan",
                                    fontSize = 10.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
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


