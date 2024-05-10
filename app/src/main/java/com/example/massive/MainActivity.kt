package com.example.massive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.massive.navigation.Pengaduan
import com.example.massive.ui.theme.MassiveTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MassiveTheme {
                Pengaduan()
            }
        }
    }
}