package com.example.massive.ui.screen.akun

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NamaLengkap(isEditable: Boolean) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val name = sharedPreferencesManager.name ?: ""
    var namaLengkap by remember { mutableStateOf("Muhammad Aziz") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Nama Lengkap",
            color = Color.Black,
            fontFamily = poppins,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = name,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { newText -> namaLengkap = newText },
            singleLine = true,
            enabled = isEditable,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isEditable) Biru else Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(isEditable: Boolean) {
    var email by remember { mutableStateOf("azizfirdaus@gmail.com") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Email",
            color = Color.Black,
            fontFamily = poppins,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = email,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { newText -> email = newText },
            singleLine = true,
            enabled = isEditable,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isEditable) Biru else Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Telepon(isEditable: Boolean, ) {
    var telepon by remember { mutableStateOf("085156521726") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Telepon",
            color = Color.Black,
            fontFamily = poppins,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = telepon,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { newText -> telepon = newText },
            singleLine = true,
            enabled = isEditable,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isEditable) Biru else Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KataSandi(isEditable: Boolean) {
    var kataSandi by remember { mutableStateOf("azizfirdaus123") }
    val passwordVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Kata Sandi",
            color = Color.Black,
            fontFamily = poppins,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = kataSandi,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { newText -> kataSandi = newText },
            singleLine = true,
            enabled = isEditable,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isEditable) Biru else Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            trailingIcon = {
                val iconImage = if (passwordVisible.value) {
                    Icons.Outlined.Visibility
                } else {
                    Icons.Outlined.VisibilityOff
                }

                val description = if (passwordVisible.value) {
                    "Visibility Icon"
                } else {
                    "Visibility Off Icon"
                }

                IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value }
                ) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}