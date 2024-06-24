package com.example.massive.data.storage

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKey {
    const val NAME_PREF = "login_preferences"
    const val NAME_KEY = "name"
    const val PASSWORD_KEY = "password"

    val STATUS_LOGIN_KEY = booleanPreferencesKey("StatusLogin")
    val FIRST_TIME_LAUNCH_KEY = booleanPreferencesKey("first_open")
}