package com.example.massive.presentation.screen.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.massive.data.api.BeritaApi
import com.example.massive.data.storage.SharedPreferencesManager

class BeritaViewModelFactory(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val beritaApi: BeritaApi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeritaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeritaViewModel(sharedPreferencesManager, beritaApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}