package com.example.massive.ui.screen.pengaduan

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.massive.R
import com.example.massive.data.api.AduanResponse
import com.example.massive.data.api.AduanSaya
import com.example.massive.data.api.Response
import com.example.massive.data.repository.DataKomunitas
import com.example.massive.data.models.Komunitas
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.screen.akun.AduanViewModel
import com.example.massive.ui.screen.home.KomunitasItem
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ApiService {
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
}

fun getFileFromUri(context: Context, uri: Uri): File? {
    val contentResolver: ContentResolver = context.contentResolver
    val fileName: String = contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        cursor.getString(nameIndex)
    } ?: return null

    val file = File(context.cacheDir, fileName)
    contentResolver.openInputStream(uri)?.use { inputStream ->
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
    return file
}

fun createRetrofitService(token: String): ApiService {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://202.10.41.84:5000")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pengaduan3(navController: NavController) {

    val currentStep = remember { mutableStateOf(2) }
    val sheetState = rememberModalBottomSheetState()
    val pengaduanBottomSheet = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val coroutineScope = rememberCoroutineScope()
    val judul = sharedPreferencesManager.judul
    val userId = sharedPreferencesManager.userId
    val uraian = sharedPreferencesManager.uraian
    val lokasi = sharedPreferencesManager.lokasi
    val token = sharedPreferencesManager.authToken ?: return
    val imageUri = sharedPreferencesManager.imageUri
    val apiService = remember { createRetrofitService(token) }
    val viewModel: AduanViewModel = viewModel()
    val aduanList by viewModel.aduanList.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAduanList(userId.toString(), token)
    }

    fun sendData() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val judulRequestBody = judul?.toRequestBody("text/plain".toMediaTypeOrNull())
                val uraianRequestBody = uraian?.toRequestBody("text/plain".toMediaTypeOrNull())
                val lokasiRequestBody = lokasi?.toRequestBody("text/plain".toMediaTypeOrNull())
                val tanggapanRequestBody = "Tanggapan Statis".toRequestBody("text/plain".toMediaTypeOrNull())
                val statusRequestBody = "Status Statis".toRequestBody("text/plain".toMediaTypeOrNull())
                val userIDRequestBody = userId.toString().toRequestBody("text/plain".toMediaTypeOrNull())

                val imageUri = Uri.parse(sharedPreferencesManager.imageUri)
                val imageFile = getFileFromUri(context, imageUri)
                if (imageFile != null) {
                    val fotoRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    val fotoPart = MultipartBody.Part.createFormData("foto", imageFile.name, fotoRequestBody)

                    apiService.createAduan(
                        judul = judulRequestBody,
                        lokasi = lokasiRequestBody,
                        uraian = uraianRequestBody,
                        tanggapan = tanggapanRequestBody,
                        status = statusRequestBody,
                        userID = userIDRequestBody,
                        foto = fotoPart
                    )
                    Log.d("Pengaduan", "Pengaduan berhasil dikirim")
                    pengaduanBottomSheet.value = true
                } else {
                    Log.e("Pengaduan", "Error mengirim pengaduan: file is null")
                }

            } catch (e: Exception) {
                Log.e("Pengaduan", "Error mengirim pengaduan", e)
            }
        }
    }

    //BottomSheet
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(2000.dp)
            .background(color = Color.White)
    ) {
        if (pengaduanBottomSheet.value) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { pengaduanBottomSheet.value = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    Arrangement.Center,
                    Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .offset(y = (-45).dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 40.dp),
                        painter = painterResource(id = R.drawable.popupsukses),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = (-70).dp)
                            .fillMaxWidth(),
                        text = "Pengaduan Berhasil",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .offset(y = (-65).dp)
                            .fillMaxWidth(),
                        text = "Selamat! Pengaduan kamu telah dikirimkan kepada pemerintah setempat.",
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Abu,
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Home.route) },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .offset(y = (-55).dp)
                            .fillMaxWidth()
                            .heightIn(55.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(55.dp)
                                .background(
                                    brush = Brush.horizontalGradient(listOf(Biru, Biru))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Selesai",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins
                            )
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .offset(y = (-10).dp)
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            StepsProgressBar(
                numberOfSteps = 3,
                currentStep = currentStep.value
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(start = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(85.dp)
            ) {
                Text(
                    text = "Isi Form",
                    fontSize = 14.sp
                )
                Text(
                    text = "Bukti",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier.offset(x = (-7).dp),
                    text = "Pratinjau",
                    fontSize = 14.sp,
                    color = Biru
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 10.dp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 30.dp).offset(y = 5.dp),
                        text = judul.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = poppins
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 30.dp),
                        text = lokasi.toString(),
                        color = Abu,
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = rememberImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                        text = uraian.toString(),
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    sendData()
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .align(Alignment.End),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(55.dp)
                        .background(brush = Brush.horizontalGradient(listOf(Biru, Biru))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Kirim Pengaduan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}