package com.example.massive.data.api

import com.example.massive.data.models.AduanResponse
import com.example.massive.data.models.PengaduanResponse
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PengaduanApi {
    @Multipart
    @POST("/api/aduan/create")
    suspend fun createAduan(
        @Part("judul") judul: okhttp3.RequestBody?,
        @Part("lokasi") lokasi: okhttp3.RequestBody?,
        @Part("uraian") uraian: okhttp3.RequestBody?,
        @Part("tanggapan") tanggapan: okhttp3.RequestBody,
        @Part("status") status: okhttp3.RequestBody,
        @Part("userId") userID: okhttp3.RequestBody,
        @Part foto: MultipartBody.Part
    )

    @POST("/api/aduan/listuser")
    suspend fun getAduanList(
        @Header("Authorization") auth: String,
        @Body data: MultipartBody
    ): Response<AduanResponse>

    @DELETE("aduan/delete/{id}")
    suspend fun deleteAduan(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Unit>
}
