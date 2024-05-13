package com.example.massive.ui.screen.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.massive.Massive
import com.example.massive.ui.screen.chatbot.Chatbot2
import com.example.massive.ui.screen.LoginScreen
import com.example.massive.ui.screen.Onboarding1
import com.example.massive.ui.screen.Onboarding2
import com.example.massive.ui.screen.Onboarding3
import com.example.massive.ui.screen.Register

@SuppressLint("SuspiciousIndentation")
@Composable
fun Pengaduan() {
    val navController = rememberNavController()
        Crossfade(targetState = PengaduanNavigation.currentScreen.value, label = "") { currentState ->
            when (currentState) {
                is Screen.Onboarding1 -> Onboarding1(navController)
                is Screen.Onboarding2 -> Onboarding2(navController)
                is Screen.Onboarding3 -> Onboarding3(navController)
                is Screen.Login -> LoginScreen(navController)
                is Screen.Register -> Register(navController)
                is Screen.Home -> Massive()
                else -> {}
            }
        }
}

