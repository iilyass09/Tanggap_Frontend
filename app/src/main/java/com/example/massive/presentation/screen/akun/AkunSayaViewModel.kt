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

class AkunSayaViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    var update by mutableStateOf<UpdateUser?>(null)
        private set

    var isLoading by mutableStateOf(false)
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

    fun updateUser(context: Context, updatedUser: UpdateUser) {
        isLoading = true
        val tag = "AkunSayaViewModel"

        viewModelScope.launch {
            val sharedPreferencesManager = SharedPreferencesManager(context)
            val userId = sharedPreferencesManager.userId

            withContext(Dispatchers.IO) {
                try {
                    Log.d(tag, "Memulai proses pembaruan pengguna.")
                    val apiService = UserInstance.getUserApi(context)
                    val response = apiService.updateUser(userId, updatedUser)

                    if (response.status == 200) {
                        Log.d(tag, "Pembaruan berhasil: ${response.data}")
                        update = response.data
                    } else {
                        Log.e(tag, "Pembaruan gagal: Status = ${response.status}, Pesan = ${response.message}")
                    }
                } catch (e: Exception) {
                    val errorMessage = e.message ?: "Unknown error"
                    val responseBody = e.localizedMessage ?: "No response body"
                    Log.e(tag, "Terjadi kesalahan saat memperbarui pengguna: $errorMessage")
                    Log.e(tag, "Response body: $responseBody", e)
                    e.printStackTrace()
                }
            }
            isLoading = false
            Log.d(tag, "Proses pembaruan selesai.")
        }
    }
}
