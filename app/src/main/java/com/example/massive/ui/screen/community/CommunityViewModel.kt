package com.example.massive.ui.screen.community

import com.example.massive.data.api.Response
import com.example.massive.data.storage.RetrofitInstance
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CommunityViewModel(application: Application) : AndroidViewModel(application) {

    val aduanResponse: MutableLiveData<Response> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchAduanBaru() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.aduanApi.getAduanBaru()
                if (response.status == 201) {
                    aduanResponse.value = response
                } else {
                    errorMessage.value = "Error: ${response.message}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Exception: ${e.message}"
            }
        }
    }
}
