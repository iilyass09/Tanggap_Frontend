package com.example.massive.ui.screen.akun

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.api.AduanResponse
import com.example.massive.data.api.AduanSaya
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

class AduanViewModel : ViewModel() {
    private val _aduanList = MutableLiveData<List<AduanSaya>>()
    val aduanList: LiveData<List<AduanSaya>> get() = _aduanList

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://202.10.41.84:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun fetchAduanList(idUser: String, token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getAduanList(mapOf("iduser" to idUser), "Bearer $token")
                if (response.status == 200) {
                    _aduanList.postValue(response.data)
                } else {
                    Log.d("Response", "Response: ${response.status}")
                    Log.e("AduanViewModel", "Failed to fetch aduan list: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("AduanViewModel", "Error fetching aduan list", e)
            }
        }
    }

    interface ApiService {
        @POST("aduan/listuser/")
        suspend fun getAduanList(@Body body: Map<String, String>, @Header("Authorization") token: String): AduanResponse
    }
}
