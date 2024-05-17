package com.example.massive.ui.screen.akun

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.massive.data.UserDataStore
import com.example.massive.data.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AkunScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val keluarBottomSheet = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userDataStore = UserDataStore(context)
    val name = sharedPreferencesManager.name ?: ""

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .background(color = Color.White)
    ) {
        if (keluarBottomSheet.value) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { keluarBottomSheet.value = false }
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

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .offset(y = 10.dp)
                        .width(150.dp)
                        .height(150.dp),
                    painter = painterResource(id = R.drawable.akun),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = name,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.AkunSaya.route) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.akunsaya),
                        contentDescription = null,
                        Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Akun Saya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pengaduansaya),
                        contentDescription = null,
                        Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Pengaduan Saya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.Pengaturan.route) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pengaturan),
                        contentDescription = null,
                        Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Pengaturan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.HubungiKami.route) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hubungikami),
                        contentDescription = null,
                        Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Hubungi Kami",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { keluarBottomSheet.value = true }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.keluar),
                        contentDescription = null,
                        Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Keluar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                }
            }
        }
    )
}