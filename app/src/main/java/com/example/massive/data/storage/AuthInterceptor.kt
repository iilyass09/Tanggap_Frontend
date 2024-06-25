package com.example.massive.data.storage

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.massive.data.api.UserApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserInstance {
    private const val BASE_URL = "http://202.10.41.84:5000/api/"

    fun getUserApi(context: Context): UserApi {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val token = sharedPreferencesManager.authToken

        val okHttpClient = OkHttpClient.Builder().apply {
            token?.let {
                addInterceptor(AuthInterceptor(it))
            }
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserApi::class.java)
    }
}


class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithToken = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(requestWithToken)
    }
}
