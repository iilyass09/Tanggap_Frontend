package com.example.massive.ui.screen.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.massive.data.models.RegisterResponse
import com.example.massive.data.storage.RetrofitInstance

class RegisterViewModel : ViewModel() {
    suspend fun registerUser(
        namaDepan: String,
        namaBelakang: String,
        email: String,
        password: String,
        level: String = "member",
        aktif: String = "1"
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
