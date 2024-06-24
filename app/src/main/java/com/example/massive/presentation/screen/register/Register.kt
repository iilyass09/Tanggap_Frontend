package com.example.massive.presentation.screen.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.presentation.navigation.Screen
import com.example.massive.presentation.components.NameTextField
import com.example.massive.presentation.components.PasswordTextField
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val level = "member"
    val aktif = "1"

    RegisterContent(
        namaDepan = namaDepan,
        namaBelakang = namaBelakang,
        email = email,
        password = password,
        onNamaDepanChange = { namaDepan = it },
        onNamaBelakangChange = { namaBelakang = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onRegisterClick = {
            if (namaDepan.isBlank() || namaBelakang.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Semua kolom harus diisi dan valid", Toast.LENGTH_SHORT).show()
            } else {
                coroutineScope.launch {
                    try {
                        val registerResponse = registerViewModel.registerUser(
                            namaDepan, namaBelakang, email, password, level, aktif
                        )
                        if (registerResponse != null && registerResponse.success) {
                            Toast.makeText(context, "Registrasi Berhasil", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Splash.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Registrasi Berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Register.route) {
                                    inclusive = true
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("RegisterError", "Error: ${e.message}")
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        onLoginClick = {
            navController.navigate(Screen.Login.route)
        },
        modifier = modifier
    )
}


@Composable
fun RegisterContent(
    namaDepan: String,
    namaBelakang: String,
    email: String,
    password: String,
    onNamaDepanChange: (String) -> Unit,
    onNamaBelakangChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Daftar",
                fontSize = 25.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = null,
                modifier = Modifier
                    .width(175.dp)
                    .height(175.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(40.dp))
            NameTextField(
                value = namaDepan,
                onValueChange = onNamaDepanChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Person",
                label = "Nama Depan",
            )
            NameTextField(
                value = namaBelakang,
                onValueChange = onNamaBelakangChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Person",
                label = "Nama Belakang",
            )
            NameTextField(
                value = email,
                onValueChange = onEmailChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Email",
                label = "Email",
            )
            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = "Kata Sandi"
            )
            Button(
                onClick = onRegisterClick,
                colors = ButtonDefaults.buttonColors(Biru),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "Daftar",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sudah Punya Akun?",
                    fontFamily = poppins,
                    fontSize = 15.sp,
                )
                TextButton(onClick = onLoginClick) {
                    Text(
                        modifier = Modifier.offset(x = (-7).dp),
                        text = "Masuk",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Biru,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(160.dp))
        }
    }
}