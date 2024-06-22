package com.example.massive.ui.screen.pengaduan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.models.Aduan
import com.example.massive.data.models.PengaduanResponse
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PengaduanViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferencesManager = SharedPreferencesManager(application)

    fun createAduan(
        aduan: Aduan,
        onSuccess: (PengaduanResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val token = sharedPreferencesManager.authToken
                val userId = sharedPreferencesManager.userId

                if (token != null && userId != -1) {
                    val aduanWithUserId = aduan.copy(userId = userId)
                    val gson = Gson()
                    Log.d("Pengaduan", "Token: Bearer $token")
                    Log.d("Pengaduan", "Payload: ${gson.toJson(aduanWithUserId)}")

                    // Tambahkan log untuk setiap field
                    Log.d("Pengaduan", "User ID: ${aduanWithUserId.userId}")
                    Log.d("Pengaduan", "Judul: ${aduanWithUserId.judul}")
                    Log.d("Pengaduan", "Lokasi: ${aduanWithUserId.lokasi}")
                    Log.d("Pengaduan", "Uraian: ${aduanWithUserId.uraian}")
                    Log.d("Pengaduan", "Foto: ${aduanWithUserId.foto}")
                    Log.d("Pengaduan", "Tanggapan: ${aduanWithUserId.tanggapan}")
                    Log.d("Pengaduan", "Status: ${aduanWithUserId.status}")

                    val response = RetrofitInstance.pengaduanApi.createAduan(
                        "Bearer $token",
                        aduanWithUserId.userId,
                        aduanWithUserId.judul,
                        aduanWithUserId.lokasi,
                        aduanWithUserId.uraian,
                        aduanWithUserId.foto,
                        aduanWithUserId.tanggapan,
                        aduanWithUserId.status
                    )
                    onSuccess(response)
                } else {
                    onError(Exception("Token atau ID Pengguna tidak ditemukan"))
                }
            } catch (e: IOException) {
                onError(Exception("Error jaringan: ${e.message}"))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("Pengaduan", "Error HTTP: ${e.message()}")
                Log.e("Pengaduan", "Response body: $errorBody")
                onError(Exception("Error HTTP: ${e.message()}"))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
