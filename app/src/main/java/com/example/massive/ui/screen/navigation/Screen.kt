package com.example.massive.ui.screen.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(val route : String) {
    object Panduan : Screen("panduan")
    object Onboarding1 : Screen("splash1")
    object Onboarding2 : Screen("splash2")
    object Onboarding3 : Screen("splash3")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Community : Screen("community")
    object Pengaduan : Screen("pengaduan")
    object Berita : Screen("berita")
    object DetailBerita : Screen("detail_berita")
    object Akun : Screen("akun")
    object Chatbot : Screen("chatbot")
    object Chatbot2 : Screen("chatbot2")
}

object PengaduanNavigation {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Onboarding1)

    fun goToPanduan(){
        currentScreen.value = Screen.Panduan
    }
    fun goToSplash1(){
        currentScreen.value = Screen.Onboarding1
    }
    fun goToSplash2(){
        currentScreen.value = Screen.Onboarding2
    }
    fun goToSplash3(){
        currentScreen.value = Screen.Onboarding3
    }
    fun goTologin(){
        currentScreen.value = Screen.Login
    }
    fun goToRegister(){
        currentScreen.value = Screen.Register
    }
    fun goToHome(){
        currentScreen.value = Screen.Home
    }
}
