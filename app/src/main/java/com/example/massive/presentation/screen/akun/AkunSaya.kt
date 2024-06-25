package com.example.massive.presentation.screen.akun

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.data.models.UpdateUser
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.componentsShapes
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AkunSaya(
    navController: NavController
) {
    val userViewModel: AkunSayaViewModel = viewModel()
    val context = LocalContext.current
    var isEditable by remember { mutableStateOf(false) }
    var firstName by remember { mutableStateOf(userViewModel.user?.nama_depan ?: "") }
    var lastName by remember { mutableStateOf(userViewModel.user?.nama_belakang ?: "") }
    var email by remember { mutableStateOf(userViewModel.user?.email ?: "") }
    var password by remember { mutableStateOf(userViewModel.user?.password ?: "") }

    LaunchedEffect(Unit) {
        userViewModel.fetchUser(context)
    }


    LaunchedEffect(userViewModel.user) {
        userViewModel.user?.let { user ->
            firstName = user.nama_depan
            lastName = user.nama_belakang
            email = user.email
            password = user.password
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (userViewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(8.dp))
            } else if (userViewModel.user != null) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("Nama Depan") },
                    enabled = isEditable,
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(componentsShapes.small),
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Nama Belakang") },
                    enabled = isEditable,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Biru,
                        focusedLabelColor = Biru,
                        cursorColor = Biru,
                    ),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(componentsShapes.small),
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    enabled = isEditable,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Biru,
                        focusedLabelColor = Biru,
                        cursorColor = Biru,
                    ),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(componentsShapes.small),
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    enabled = isEditable,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Biru,
                        focusedLabelColor = Biru,
                        cursorColor = Biru,
                    ),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(componentsShapes.small),
                )
            } else {
                Text(
                    text = "Tidak ada data pengguna.",
                    modifier = Modifier
                        .padding(8.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (isEditable) {
                        val sharedPreferencesManager = SharedPreferencesManager(context)
                        val token = sharedPreferencesManager.authToken ?: return@Button

                        val updateUser = UpdateUser(
                            id = userViewModel.user?.id ?: return@Button,
                            namadepan = firstName,
                            namabelakang = lastName,
                            email = email,
                            password = password,
                        )

                        userViewModel.updateUser(context, updateUser, token)
                        navController.navigate(Screen.Akun.route)
                    }
                    isEditable = !isEditable

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
                        text = if (isEditable) "Simpan" else "Edit Akun",
                        color = Color.White,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
