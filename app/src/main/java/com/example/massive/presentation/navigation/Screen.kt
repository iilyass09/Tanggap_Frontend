package com.example.massive.presentation.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(val route : String) {
    object Splash : Screen("splash")
    object Panduan : Screen("panduan")
    object Onboarding1 : Screen("splash1")
    object Onboarding2 : Screen("splash2")
    object Onboarding3 : Screen("splash3")
    object Login : Screen("login")
    object Register : Screen("register")
    object FPassword1 : Screen("FPassword1")
    object FPassword2 : Screen("FPassword2")
    object FPassword3 : Screen("FPassword3")
    object Home : Screen("home")
    object Pantau : Screen("pantau")
    object DetailCommunity : Screen("detail_community")
    object Pengaduan : Screen("pengaduan")
    object Pengaduan2 : Screen("pengaduan2")
    object Pengaduan3 : Screen("pengaduan3")
    object Berita : Screen("berita")
    object BeritaDetail : Screen("berita_detail")
    object Akun : Screen("akun")
    object AkunSaya : Screen("akun_saya")
    object PengaduanSaya : Screen("pengaduan_saya")
    object DetailPengaduanSaya : Screen("detail_pengaduan_saya")
    object HubungiKami : Screen("hubungi_kami")
    object Pengaturan : Screen("pengaturan")
    object Bahasa : Screen("bahasa")
    object Bantuan : Screen("bantuan")
    object KebijakanPrivasi : Screen("kebijakan_privasi")
    object Chatbot : Screen("chatbot")
    object Chatbot2 : Screen("chatbot2")
    object CustomerService : Screen("customer_service")
}