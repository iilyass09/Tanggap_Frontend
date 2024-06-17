package com.example.massive.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ChatbotApi {
    @POST("chatbot")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>
}

data class ChatRequest(val message: String)
data class ChatResponse(val response: String)