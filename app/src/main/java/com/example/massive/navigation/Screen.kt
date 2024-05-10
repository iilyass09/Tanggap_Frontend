package com.example.massive.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(val route : String) {
    object Splash1 : Screen("splash1")
    object Splash2 : Screen("splash2")
    object Splash3 : Screen("splash3")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Community : Screen("community")
    object Pengaduan : Screen("pengaduan")
    object Berita : Screen("berita")
    object Akun : Screen("akun")
    object Chatbot : Screen("chatbot")
}

object PengaduanNavigation {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Splash1)

    fun goToSplash1(){
        currentScreen.value = Screen.Splash1
    }
    fun goToSplash2(){
        currentScreen.value = Screen.Splash2
    }
    fun goToSplash3(){
        currentScreen.value = Screen.Splash3
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
