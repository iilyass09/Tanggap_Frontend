package com.example.massive.ui.screen.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.massive.data.api.RegisterResponse
import com.example.massive.data.storage.RetrofitInstance
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    suspend fun registerUser(
        namaDepan: String,
        namaBelakang: String,
        email: String,
        password: String,
        level: String,
        aktif: String
    ): RegisterResponse? {
        return try {
            val response = RetrofitInstance.registerApi.registerUser(
                namaDepan, namaBelakang, email, password, level, aktif
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("RegisterViewModel", "Registration failed: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "Error: ${e.message}")
            null
        }
    }
}