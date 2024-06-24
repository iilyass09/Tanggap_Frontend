package com.example.massive.presentation.screen.community

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.AduanCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val viewModel: CommunityViewModel = viewModel()
    val aduanList = viewModel.aduanList
    val authToken = sharedPreferencesManager.authToken

    authToken?.let {
        LaunchedEffect(authToken) {
            viewModel.fetchAduan(authToken)
        }
    } ?: run {
        Text(
            text = "Token tidak ditemukan",
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
    }
    if (aduanList.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholder = {
                        Text("Cari nama pengguna")
                    },
                    shape = RoundedCornerShape(20),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                    )
                )
            }
            items(
                aduanList.filter { aduan ->
                    val fullName = "${aduan.User.nama_depan} ${aduan.User.nama_belakang}"
                    fullName.contains(searchQuery, ignoreCase = true) ||
                            aduan.User.nama_depan.contains(searchQuery, ignoreCase = true) ||
                            aduan.User.nama_belakang.contains(searchQuery, ignoreCase = true)
                }
            ) { aduan ->
                AduanCard(aduan = aduan)
            }
        }
    } else {
        Text(
            text = "Tidak ada aduan yang tersedia",
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
    }
}
