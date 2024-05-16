package com.example.massive.ui.screen.akun

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.example.massive.ui.theme.Biru


@Composable
fun AkunSaya(
    navController: NavController
) {
    var isEditable by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NamaLengkap(isEditable)
            Spacer(modifier = Modifier.height(15.dp))
            Email(isEditable)
            Spacer(modifier = Modifier.height(15.dp))
            Telepon(isEditable)
            Spacer(modifier = Modifier.height(15.dp))
            KataSandi(isEditable)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {isEditable = !isEditable},
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(Biru),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(text = if (isEditable) "Simpan" else "Edit Akun", color = Color.White)
            }
        }
    }
}


@Preview
@Composable
fun AkunSayaPrev() {
    AkunSaya(navController = rememberNavController())
}