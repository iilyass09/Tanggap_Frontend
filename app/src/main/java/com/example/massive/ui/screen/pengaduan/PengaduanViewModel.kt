package com.example.massive.ui.screen.pengaduan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.massive.data.api.Aduan
import com.example.massive.data.api.PengaduanResponse
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.data.storage.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PengaduanViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferencesManager = SharedPreferencesManager(application)

    fun createAduan(aduan: Aduan, onSuccess: (PengaduanResponse) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val token = sharedPreferencesManager.getToken()
                if (token != null) {
                    val userId = getUserIdFromToken(token)
                    val aduanWithUserId = aduan.copy(userId = userId)
                    val response = RetrofitInstance.pengaduanApi.createAduan("Bearer $token", aduanWithUserId)
                    onSuccess(response)
                } else {
                    onError(Exception("Token tidak ditemukan"))
                }
            } catch (e: IOException) {
                onError(Exception("Error jaringan: ${e.message}"))
            } catch (e: HttpException) {
                onError(Exception("Error HTTP: ${e.message}"))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }


    private fun getUserIdFromToken(token: String): String {
        val jwt = JWT(token)
        return jwt.getClaim("userId").asString() ?: throw Exception("User ID tidak ditemukan dalam token")
    }
}
