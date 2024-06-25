package com.example.massive.presentation.screen.akun

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.models.UpdateUser
import com.example.massive.data.models.User
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.data.storage.UserInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AkunSayaViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    var update by mutableStateOf<UpdateUser?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchUser(context: Context) {
        isLoading = true

        viewModelScope.launch {
            val sharedPreferencesManager = SharedPreferencesManager(context)
            val userId = sharedPreferencesManager.userId

            val fetchedUser = withContext(Dispatchers.IO) {
                try {
                    val apiService = UserInstance.getUserApi(context)
                    val response = apiService.getUserById(userId)
                    if (response.status ==200) {
                        response.data
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
            user = fetchedUser
            isLoading = false
        }
    }
    fun updateUser(context: Context, updateUser: UpdateUser, token: String) {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            val updateUserResponse = withContext(Dispatchers.IO) {
                try {
                    val apiService = UserInstance.getUserApi(context)
                    val response = apiService.updateUser(
                        updateUser.id,
                        updateUser.namadepan,
                        updateUser.namabelakang,
                        updateUser.email,
                        updateUser.password,
                        "member", // Nilai statis
                        'Y' // Nilai statis
                    )
                    if (response.status == 200) {
                        response

                    } else {
                        errorMessage = "Gagal mengupdate user: ${response.message}"
                        null
                    }
                } catch (e: HttpException) {
                    Log.e("AkunSayaViewModel", "HTTP error: ${e.response()?.errorBody()?.string()}", e)
                    errorMessage = "Terjadi kesalahan: ${e.response()?.errorBody()?.string()}"
                    null
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("AkunSayaViewModel", "Error updating user", e)
                    errorMessage = "Terjadi kesalahan: ${e.localizedMessage}"
                    null
                }
            }

            if (updateUserResponse != null) {
                update = updateUserResponse.data
            }
            isLoading = false
        }
    }

}
