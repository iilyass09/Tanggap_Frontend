package com.example.massive.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.R
import com.example.massive.data.UserDataStore
import com.example.massive.data.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userDataStore = UserDataStore(context)
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginContent(
        name = name,
        password = password,
        onNameChange = { name = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            if (name.isBlank() || password.isBlank()){
                Toast.makeText(context, "Nama dan Password Wajib Diisi", Toast.LENGTH_SHORT).show()
            } else {
                sharedPreferencesManager.name = name
                sharedPreferencesManager.password = password
                coroutineScope.launch {
                    userDataStore.saveStatus(true)
                }
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
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
            TextButton(
                onClick = moveToForgot,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Lupa Kata Sandi?",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Biru
                )
            }
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
    }
}

@Preview
@Composable
fun LoginPrev() {
    LoginScreen(navController = rememberNavController())
}