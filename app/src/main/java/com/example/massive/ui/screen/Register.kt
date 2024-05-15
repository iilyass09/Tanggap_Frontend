package com.example.massive.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val registerBottomSheet = rememberSaveable { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.password_visibility)
    else
        painterResource(id = R.drawable.password_visibility_off)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        if (registerBottomSheet.value) {
            ModalBottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(800.dp),
                sheetState = sheetState,
                onDismissRequest = { registerBottomSheet.value = false }
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
                            .offset(y = (-40).dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 50.dp),
                        painter = painterResource(id = R.drawable.popupdaftar),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = (-40).dp)
                            .fillMaxWidth(),
                        text = "Apakah data anda sudah benar?",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Login.route) },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-30).dp)
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
                                text = "Benar",
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

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(28.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Daftar",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(25.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = poppins
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
            Spacer(modifier = Modifier.height(30.dp))

            // TEXTFIELD USERNAME
            val textUsername = remember { mutableStateOf("") }
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Buat nama pengguna") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textUsername.value,
                onValueChange = {
                    textUsername.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD EMAIL
            val textEmail = remember { mutableStateOf("") }
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Buat alamat email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textEmail.value,
                onValueChange = {
                    textEmail.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD PASSWORD
            val textPassword = remember { mutableStateOf("") }
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Buat kata sandi") },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
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
                if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textPassword.value,
                onValueChange = {
                    textPassword.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD KONFIRMASI PASSWORD
            val textConfirmPassword = remember { mutableStateOf("") }
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Konfirmasi kata sandi") },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
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
                if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textConfirmPassword.value,
                onValueChange = {
                    textConfirmPassword.value = it
                },
            )
            Spacer(modifier = Modifier.height(30.dp))

            // BUTTON REGISTER
            Button(
                onClick = { registerBottomSheet.value = true },
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
                        text = "Daftar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // TEKS LOGIN
            ClickableLoginTextComponent(
                onTextSelected = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}

@Composable
fun ClickableLoginTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Sudah Punya Akun?  "
    val loginText = "Masuk"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Biru)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
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
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        }
    )
}