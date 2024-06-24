package com.example.massive.presentation.screen.login

import androidx.lifecycle.ViewModel
import com.example.massive.data.models.LoginResponse
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.data.storage.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun login(email: String, password: String, sharedPreferencesManager: SharedPreferencesManager): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                _isLoading.value = true

                val response = RetrofitInstance.loginApi.login(email, password)
                response.let {
                    sharedPreferencesManager.authToken = it.data.token
                    sharedPreferencesManager.userId = it.data.id
                }

                _isLoading.value = false

                response
            } catch (e: Exception) {
                _isLoading.value = false
                null
            }
        }
    }
}