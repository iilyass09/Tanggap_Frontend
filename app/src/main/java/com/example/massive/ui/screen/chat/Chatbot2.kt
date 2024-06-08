package com.example.massive.ui.screen.chat

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.api.ChatRequest
import com.example.massive.data.api.ChatResponse
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Chatbot2(navController: NavController) {
    val chatMessages = remember { mutableStateListOf<Pair<String, Boolean>>() }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            ChatbotTopBar(navController)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(chatMessages) { message ->
                    ChatBubble(message = message.first, isUser = message.second)
                }
            }
            SendMessageTextField(chatMessages)
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
fun SendMessageTextField(chatMessages: MutableList<Pair<String, Boolean>>) {
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                    onClick = {
                        if (message.isNotEmpty()) {
                            chatMessages.add(Pair(message, true))
                            val api = RetrofitInstance.api
                            val call = api.sendMessage(ChatRequest(message))
                            call.enqueue(object : Callback<ChatResponse> {
                                override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                                    if (response.isSuccessful) {
                                        response.body()?.let {
                                            chatMessages.add(Pair(it.response, false))
                                        }
                                    } else {
                                        Toast.makeText(context, "Failed to get response", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                            message = ""
                        }
                    },
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

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Row(
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))

    ) {
        Box(
            modifier = Modifier
                .background(if (isUser) Biru else Abu2)
                .padding(10.dp)
        ) {
            Text(
                text = message,
                color = if (isUser) Color.White else Color.Black
            )
        }
    }
}
