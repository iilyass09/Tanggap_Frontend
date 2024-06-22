package com.example.massive.ui.screen.akun

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.api.PengaduanSayaApi
import com.example.massive.data.models.AduanResponse
import com.example.massive.data.models.AduanSaya
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response
import okhttp3.MultipartBody

class AduanViewModel : ViewModel() {
    private val _aduanList = MutableLiveData<List<AduanSaya>>()
    val aduanList: LiveData<List<AduanSaya>> get() = _aduanList

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://202.10.41.84:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(PengaduanSayaApi::class.java)

    fun fetchAduanList(idUser: String, token: String) {
        viewModelScope.launch {
            try {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("iduser", idUser)
                    .build()

                Log.d("AduanViewModel", "Request body: $requestBody")
                Log.d("AduanViewModel", "Authorization token: Bearer $token")

                val response: Response<AduanResponse> = service.getAduanList("Bearer $token", requestBody)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status == 200) {
                        _aduanList.postValue(responseBody.data)
                    } else {
                        Log.e("AduanViewModel", "Failed to fetch aduan list: ${responseBody?.message}")
                    }
                } else {
                    Log.e("AduanViewModel", "HTTP error code: ${response.code()}, message: ${response.message()}")
                }
            } catch (e: HttpException) {
                Log.e("AduanViewModel", "HTTP error code: ${e.code()}, message: ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("AduanViewModel", "Error fetching aduan list", e)
            }
        }
    }
}
