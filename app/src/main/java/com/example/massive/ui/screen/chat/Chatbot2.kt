package com.example.massive.ui.screen.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.poppins

@Composable
fun Chatbot2(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column{
            ChatbotTopBar(navController)
            Spacer(modifier = Modifier.weight(1f))
            SendMessageTextField()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotTopBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Abu2),
        title = {
            Column(
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier.offset(x = 5.dp, y = 7.dp),
                    text = "Chatbot",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                )
                Text(
                    modifier = Modifier.offset(x = 5.dp, y = (-2).dp),
                    text = "Online",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins
                )
            }
        },
        navigationIcon = {
            Box{
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.chatbot),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 20.dp)
                        .size(40.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.popBackStack() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.exitchatbot),
                    contentDescription = "Notif",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(23.dp)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMessageTextField() {
    var message by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Kirim pesan...") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Abu2,
                cursorColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send message"
                    )
                }
            }
        )
    }
}
