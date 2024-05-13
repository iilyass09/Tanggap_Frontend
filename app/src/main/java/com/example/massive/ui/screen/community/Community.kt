package com.example.massive.ui.screen.community

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.massive.data.DataKomunitas
import com.example.massive.data.Komunitas
import com.example.massive.ui.screen.home.KomunitasItem

@Composable
fun CommunityScreen(navController: NavController) {
    val komunitass: List<Komunitas> = DataKomunitas.ListKomunitas
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy((-15).dp),
                modifier = Modifier.fillMaxSize().offset(y = (-5).dp)
            ) {
                items(komunitass.take(10)) { komunitas ->
                    KomunitasItem(komunitas = komunitas)
                }
            }
        }
    }
}
