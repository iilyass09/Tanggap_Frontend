package com.example.massive.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.EarthquakeApp.screen.Splash3
import com.example.massive.Massive
import com.example.massive.screen.BeritaScreen
import com.example.massive.screen.ChatbotScreen
import com.example.massive.screen.LoginScreen
import com.example.massive.screen.Register
import com.example.massive.screen.Splash1
import com.example.massive.screen.Splash2

@Composable
fun Pengaduan() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PengaduanNavigation.currentScreen.value, label = "") { currentState ->
            when (currentState) {
                is Screen.Splash1 -> Splash1(navController)
                is Screen.Splash2 -> Splash2(navController)
                is Screen.Splash3 -> Splash3(navController)
                is Screen.Login -> LoginScreen(navController)
                is Screen.Register -> Register(navController)
                is Screen.Home -> Massive()
                else -> {}
            }
        }
    }
}
