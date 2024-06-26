package com.example.massive.presentation.screen.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.massive.data.api.BeritaApi
import com.example.massive.data.models.Berita
import com.example.massive.data.storage.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BeritaViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val beritaApi: BeritaApi
) : ViewModel() {

    private val _beritaList = MutableStateFlow<List<Berita>>(emptyList())
    val beritaList: StateFlow<List<Berita>> = _beritaList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchBerita(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val response = beritaApi.getBerita("Bearer $token")
                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    _beritaList.emit(data)
                    _errorMessage.emit(null)
                } else {
                    _errorMessage.emit("Gagal memuat data berita")
                }
            } catch (e: IOException) {
                _errorMessage.emit("Error jaringan: ${e.message}")
            } catch (e: HttpException) {
                _errorMessage.emit("Error HTTP: ${e.message}")
            } catch (e: Exception) {
                _errorMessage.emit("Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
