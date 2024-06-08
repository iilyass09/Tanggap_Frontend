package com.example.massive.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.massive.data.models.Berita
import com.example.massive.data.models.Komunitas
import com.example.massive.data.repository.DataBerita
import com.example.massive.data.repository.DataKomunitas

class HomeViewModel : ViewModel() {
    private val _komunitasList = MutableStateFlow<List<Komunitas>>(emptyList())
    val komunitasList: StateFlow<List<Komunitas>> = _komunitasList

    private val _beritaList = MutableStateFlow<List<Berita>>(emptyList())
    val beritaList: StateFlow<List<Berita>> = _beritaList

    init {
        fetchKomunitas()
        fetchBerita()
    }

    private fun fetchKomunitas() {
        viewModelScope.launch {
            _komunitasList.value = DataKomunitas.ListKomunitas
        }
    }

    private fun fetchBerita() {
        viewModelScope.launch {
            _beritaList.value = DataBerita.ListBerita
        }
    }
}
