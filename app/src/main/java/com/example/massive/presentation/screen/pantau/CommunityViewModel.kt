package com.example.massive.presentation.screen.pantau

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.models.AduanResponse
import com.example.massive.data.models.AduanSaya
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

class PantauViewModel : ViewModel() {

    interface CommunityApi {
        @GET("api/aduan/get/baru")
        suspend fun getAllAduan(@Header("Authorization") token: String): Response<AduanResponse>
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://202.10.41.84:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(CommunityApi::class.java)

    private val _aduanList = mutableStateListOf<AduanSaya>()
    val aduanList: List<AduanSaya> get() = _aduanList

    fun fetchAduan(authToken: String) {
        viewModelScope.launch {
            try {
                val response = api.getAllAduan("Bearer $authToken")
                println("Response Code: ${response.code()}")
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _aduanList.clear()
                        _aduanList.addAll(it)
                        println("Data diterima: ${_aduanList.size} items")
                    }
                } else {
                    println("Response tidak berhasil: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Exception terjadi: $e")
            }
        }
    }

}

