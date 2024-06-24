package com.example.massive.presentation.screen.pengaduan

import android.util.Log
import androidx.lifecycle.*
import com.example.massive.data.api.PengaduanApi
import com.example.massive.data.models.AduanResponse
import com.example.massive.data.models.AduanSaya
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response
import okhttp3.MultipartBody

class PengaduanViewModel : ViewModel() {
    private val _aduanList = MutableLiveData<List<AduanSaya>>()
    val aduanList: LiveData<List<AduanSaya>> get() = _aduanList

    private val _deletionResult = MutableLiveData<Result<String>>()
    val deletionResult: LiveData<Result<String>> = _deletionResult

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://202.10.41.84:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(PengaduanApi::class.java)

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

    fun deleteAduan(id: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = service.deleteAduan("Bearer $token", id)
                if (response.isSuccessful) {
                    _deletionResult.postValue(Result.success("Aduan deleted successfully"))
                    // Refresh the list after deletion
                    _aduanList.value = _aduanList.value?.filterNot { it.id == id }
                } else {
                    _deletionResult.postValue(Result.failure(Throwable("Failed to delete Aduan: ${response.message()}")))
                }
            } catch (e: HttpException) {
                Log.e("AduanViewModel", "HTTP error deleting Aduan: ${e.message()}", e)
                _deletionResult.postValue(Result.failure(Throwable("HTTP error deleting Aduan: ${e.message()}")))
            } catch (e: Exception) {
                Log.e("AduanViewModel", "Error deleting Aduan", e)
                _deletionResult.postValue(Result.failure(Throwable("Error deleting Aduan")))
            }
        }
    }
}
