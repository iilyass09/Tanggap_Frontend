package com.example.massive.data.api

import com.example.massive.data.models.ChatRequest
import com.example.massive.data.models.ChatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ChatbotApi {
    @POST("chatbot")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>
}
