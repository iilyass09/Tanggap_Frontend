package com.example.massive.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.presentation.navigation.Screen
import com.example.massive.presentation.screen.akun.AkunSayaViewModel
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController) {
    val context = LocalContext.current
    val userViewModel: AkunSayaViewModel = viewModel()

    LaunchedEffect(Unit) {
        userViewModel.fetchUser(context)
    }
    if (userViewModel.isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
    } else if (userViewModel.user != null) {
        val firstname by remember { mutableStateOf(userViewModel.user?.nama_depan ?: "") }
        val lastname by remember { mutableStateOf(userViewModel.user?.nama_belakang ?: "") }

        TopAppBar(
            title = {
                Text(
                    text = "$firstname $lastname",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                )
            },
            navigationIcon = {
                Box {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(7.dp)
                            .size(40.dp)
                            .clickable { navController.navigate(Screen.Akun.route) }
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.notif),
                        contentDescription = "Notif",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(23.dp)
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotTopBar(navController: NavController) {
    val keluarChatBottomSheet = remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Abu2),
        title = {
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier.offset(x = 5.dp, y = 7.dp),
                    text = "Chatbot",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                )
                Text(
                    modifier = Modifier.offset(x = 5.dp, y = (-2).dp),
                    text = "Online",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                )
            }
        },
        navigationIcon = {
            Box{
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.chatbot),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 20.dp)
                        .size(40.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    keluarChatBottomSheet.value = true
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.exitchatbot),
                    contentDescription = "Notif",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(23.dp)
                )
            }
        },
    )
    if (keluarChatBottomSheet.value) {
        ChatbotBottomSheet(
            navController = navController,
            keluarChatbotBottomSheet = keluarChatBottomSheet
        )
    }
}