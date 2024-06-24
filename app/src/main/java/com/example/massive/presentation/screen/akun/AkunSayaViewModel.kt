package com.example.yourapp.network

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.models.User
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.data.storage.UserInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AkunSayaViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
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
                    if (response.status == 200) {
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
}
