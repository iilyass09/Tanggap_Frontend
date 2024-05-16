package com.example.massive.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
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
                coroutineScope.launch{
                    userDataStore.saveStatus(true)
                }
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            }
        },
        moveToForgot = {
            Toast.makeText(
                context,
                "Silahkan di kembangkan sendiri",
                Toast.LENGTH_SHORT
            ).show()
        },
        onGoogleClick = {
            Toast.makeText(
                context,
                "Nanti akan dibahas saat Materi Firebase",
                Toast.LENGTH_SHORT
            ).show()
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
    onGoogleClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(100.dp))


            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = null,
                modifier = Modifier
                    .width(175.dp)
                    .height(175.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = "Untuk Belajar tentang Android Development",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            NameTextField(
                value = name,
                onValueChange = onNameChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Person",
                label = "Nama",
            )
            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = "Password"
            )
            TextButton(
                onClick = moveToForgot,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Lupa Kata Sandi?",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Button(
                onClick = onLoginClick,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Masuk",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = "atau",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Belum Punya Akun?")
                TextButton(onClick = onSignUpClick) {
                    Text(
                        text = "Daftar",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(128.dp))
        }
    }
}

@Composable
fun ClickableForgotTextComponent(onTextSelected : () -> Unit) {
    val text = "Lupa Kata Sandi?"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Biru)){
            pushStringAnnotation(tag = text, annotation = text)
            append(text)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.End,
            fontFamily = poppins
        ),
        text = annotatedString, onClick = {offset ->
            annotatedString.getStringAnnotations(offset,offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent","{${span.item}}")

                    if (span.item == text) {
                        onTextSelected()
                    }
                }
        })
}

@Composable
fun ClickableRegisterTextComponent(onTextSelected : (String) -> Unit) {
    val initialText = "Belum punya akun?  "
    val registerText = "Daftar"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Biru)){
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            fontFamily = poppins
        ),
        text = annotatedString, onClick = {offset ->

            annotatedString.getStringAnnotations(offset,offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent","{${span.item}}")

                    if (span.item == registerText) {
                        onTextSelected(span.item)
                    }
                }

        })
}