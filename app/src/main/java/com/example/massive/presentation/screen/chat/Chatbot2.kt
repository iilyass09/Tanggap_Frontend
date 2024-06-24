package com.example.massive.presentation.screen.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.massive.presentation.components.ChatBubble
import com.example.massive.presentation.components.ChatbotTextField
import com.example.massive.presentation.components.ChatbotTopBar

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
            ChatbotTextField(chatMessages)
        }
    }
}
