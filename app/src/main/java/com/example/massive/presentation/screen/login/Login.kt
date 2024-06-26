package com.example.massive.presentation.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.massive.data.storage.UserDataStore
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.NameTextField
import com.example.massive.presentation.components.PasswordTextField
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel() // Obtain ViewModel instance
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userDataStore = remember { UserDataStore(context) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by loginViewModel.isLoading.collectAsState()

    LoginContent(
        name = email,
        password = password,
        onNameChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Email dan Password Wajib Diisi", Toast.LENGTH_SHORT).show()
            } else {
                coroutineScope.launch {
                    try {
                        loginViewModel.login(email, password, sharedPreferencesManager)?.let { loginResponse ->
                            if (loginResponse.data.token.isNotBlank()) {
                                val token = loginResponse.data.token
                                val userId = loginResponse.data.id
                                Log.d("Token", "Token Valid: $token")
                                Log.d("User ID", "ID Pengguna Valid: $userId")

                                sharedPreferencesManager.saveToken(token)
                                sharedPreferencesManager.userId = userId
                                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()

                                sharedPreferencesManager.name = email
                                sharedPreferencesManager.password = password
                                userDataStore.saveStatus(true)

                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Splash.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Log.d("Token", "Token Tidak Valid atau Null")
                                Toast.makeText(context, "Email atau Password anda salah", Toast.LENGTH_SHORT).show()
                            }
                        } ?: run {
                            Toast.makeText(context, "Email atau Password anda salah", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Log.e("LoginError", "Error: ${e.message}")
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        moveToForgot = {
            navController.navigate(Screen.FPassword1.route)
        },
        onSignUpClick = {
            navController.navigate(Screen.Register.route)
        },
        isLoading = isLoading,
        modifier = modifier
    )
}


@Composable
fun LoginContent(
    name: String,
    password: String,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    moveToForgot: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading : Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Masuk",
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
                value = name,
                onValueChange = onNameChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Person",
                label = "Nama Pengguna",
            )
            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = "Kata Sandi"
            )
//            TextButton(
//                onClick = moveToForgot,
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(bottom = 8.dp)
//            ) {
//                Text(
//                    text = "Lupa Kata Sandi?",
//                    style = MaterialTheme.typography.bodyLarge,
//                    fontFamily = poppins,
//                    fontWeight = FontWeight.Normal,
//                    color = Biru
//                )
//            }
            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(Biru),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "Masuk",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Belum Punya Akun?",
                    fontFamily = poppins,
                    fontSize = 15.sp,
                )
                TextButton(onClick = onSignUpClick) {
                    Text(
                        modifier = Modifier.offset(x = (-7).dp),
                        text = "Daftar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Biru,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(160.dp))
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-100).dp)
            )
        }
    }
}