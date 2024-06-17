package com.example.massive.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.massive.ui.utils.PreferencesKey.NAME_KEY
import com.example.massive.ui.utils.PreferencesKey.NAME_PREF
import com.example.massive.ui.utils.PreferencesKey.PASSWORD_KEY

class SharedPreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "token"
    }
    var authToken: String?
        get() = sharedPreferences.getString(KEY_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_TOKEN, value).apply()
        }
    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }
    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    //User
    var name
        get() = preferences.getString(NAME_KEY, "")
        set(value) {
            editor.putString(NAME_KEY, value)
            editor.commit()
        }

    var password
        get() = preferences.getString(PASSWORD_KEY, "")
        set(value) {
            editor.putString(PASSWORD_KEY, value)
            editor.commit()
        }

    fun clear() {
        editor.clear()
        editor.commit()
    }
}