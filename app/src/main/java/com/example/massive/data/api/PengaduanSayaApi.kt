package com.example.massive.data.api

import com.example.massive.data.models.AduanResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import okhttp3.MultipartBody

interface PengaduanSayaApi {
    @POST("/api/aduan/listuser")
    suspend fun getAduanList(
        @Header("Authorization") auth: String,
        @Body data: MultipartBody
    ): Response<AduanResponse>
}
