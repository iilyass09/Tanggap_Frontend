package com.example.massive.data.storage

import android.content.Context
import com.example.massive.ui.utils.PreferencesKey.NAME_KEY
import com.example.massive.ui.utils.PreferencesKey.NAME_PREF
import com.example.massive.ui.utils.PreferencesKey.PASSWORD_KEY

class SharedPreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_JUDUL = "judul"
        private const val KEY_URAIAN = "uraian"
        private const val KEY_LOKASI = "lokasi"
        private const val KEY_IMAGE_URI = "image_uri"
    }
    var authToken: String?
        get() = sharedPreferences.getString(KEY_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_TOKEN, value).apply()
        }
    var userId: Int
        get() = preferences.getInt(KEY_USER_ID, -1)
        set(value) {
            editor.putInt(KEY_USER_ID, value).apply()
        }
    //Aduan
    var judul: String?
        get() = preferences.getString(KEY_JUDUL, null)
        set(value) {
            editor.putString(KEY_JUDUL, value).apply()
        }

    var uraian: String?
        get() = preferences.getString(KEY_URAIAN, null)
        set(value) {
            editor.putString(KEY_URAIAN, value).apply()
        }

    var lokasi: String?
        get() = preferences.getString(KEY_LOKASI, null)
        set(value) {
            editor.putString(KEY_LOKASI, value).apply()
        }

    var imageUri: String?
        get() = preferences.getString(KEY_IMAGE_URI, null)
        set(value) {
            editor.putString(KEY_IMAGE_URI, value).apply()
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