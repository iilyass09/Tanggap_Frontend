package com.example.massive.ui.screen.akun

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import com.example.massive.R
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.poppins

@Composable
fun HubungiKami(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.CustomerService.route) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.customer_service),
                    contentDescription = null,
                    Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.widthIn(10.dp))
                Text(
                    text = "Customer Service",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = null,
                    Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.widthIn(10.dp))
                Text(
                    text = "WhatsApp",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.website),
                    contentDescription = null,
                    Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.widthIn(10.dp))
                Text(
                    text = "Website",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = null,
                    Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.widthIn(10.dp))
                Text(
                    text = "Instagram",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun Prev() {
    HubungiKami(navController = rememberNavController())
}