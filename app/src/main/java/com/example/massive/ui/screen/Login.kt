package com.example.massive.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.componentsShapes
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val teksPassword = remember { mutableStateOf("") }
    val teksEmail = remember { mutableStateOf("") }
    var PasswordVisibility by remember { mutableStateOf(false) }
    val icon = if (PasswordVisibility)
        painterResource(id = R.drawable.password_visibility)
    else
        painterResource(id = R.drawable.password_visibility_off)

    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column {
            Text(
                text = "Masuk",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = poppins
            )
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = null,
                modifier = Modifier
                    .width(220.dp)
                    .height(220.dp)
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = {
                    Text(
                        text = if (isEmailError) "Email harus diisi" else "Masukan email anda",
                        color = if (isEmailError) Color.Red else Color.Black
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = teksEmail.value,
                onValueChange = {
                    teksEmail.value = it
                    isEmailError = false
                },
                isError = isEmailError,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = {
                    Text(
                        text = if (isPasswordError) "Password harus diisi" else "Masukan kata sandi anda",
                        color = if (isPasswordError) Color.Red else Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                                         PasswordVisibility = !PasswordVisibility
                    }, modifier = Modifier.padding(end = 5.dp)) {
                        Icon(
                            painter = icon,
                            contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation =
                if (PasswordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = teksPassword.value,
                onValueChange = {
                    teksPassword.value = it
                    isPasswordError = false
                },
                isError = isPasswordError,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(15.dp))

            ClickableForgotTextComponent(onTextSelected = {  })
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (teksEmail.value.isBlank() && teksPassword.value.isBlank()) {
                        isEmailError = true
                        isPasswordError = true
                    } else if (teksEmail.value.isBlank()) {
                        isEmailError = true
                        isPasswordError = false
                    } else if (teksPassword.value.isBlank()) {
                        isEmailError = false
                        isPasswordError = true
                    } else {
                        navController.navigate(Screen.Home.route)
                    }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
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
                        text = "Masuk",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            ClickableRegisterTextComponent(onTextSelected = { navController.navigate(Screen.Register.route) })
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