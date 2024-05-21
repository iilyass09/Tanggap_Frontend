package com.example.massive.ui.utils

import com.example.massive.ui.navigation.Screen

fun String?.ShowBottomBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Community.route,
        Screen.Pengaduan.route,
        Screen.Pengaduan2.route,
        Screen.Pengaduan3.route,
        Screen.Berita.route,
        Screen.Akun.route
    )
}

fun String? .ShowTopBarWithIcon(): Boolean{
    return this in setOf(
        Screen.Pengaduan2.route,
        Screen.Pengaduan3.route,
        Screen.Panduan.route,
        Screen.DetailBerita.route + "/{beritaId}",
        Screen.DetailCommunity.route + "/{komunitasId}",
        Screen.Chatbot.route,
        Screen.AkunSaya.route,
        Screen.PengaduanSaya.route,
        Screen.DetailPengaduanSaya.route,
        Screen.HubungiKami.route,
        Screen.Pengaturan.route,
        Screen.Bantuan.route,
        Screen.Bahasa.route,
        Screen.KebijakanPrivasi.route
    )
}

fun String? .HideTopBar(): Boolean{
    return this !in setOf(
        Screen.Onboarding1.route,
        Screen.Onboarding2.route,
        Screen.Onboarding2.route,
        Screen.Onboarding3.route,
        Screen.Login.route,
        Screen.Register.route,
        Screen.Home.route,
        Screen.Chatbot2.route,
        Screen.CustomerService.route
    )
}

fun String? .ShowFAB(): Boolean{
    return this in setOf(
        Screen.Home.route,
        Screen.Community.route,
        Screen.Berita.route,
        Screen.Akun.route
    )
}