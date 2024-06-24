package com.example.massive.presentation.screen.pengaduan

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.massive.data.storage.RetrofitInstance.createRetrofitService
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.PengaduanBottomSheet
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pengaduan3(navController: NavController) {

    val currentStep = remember { mutableStateOf(2) }
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
    val viewModel: PengaduanViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchAduanList(userId.toString(), token)
    }

    if (pengaduanBottomSheet.value) {
        PengaduanBottomSheet(
            navController = navController,
            pengaduanBottomSheet = pengaduanBottomSheet
        )
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
            Card(
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column (
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = judul.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = poppins
                    )
                    Text(
                        modifier = Modifier.offset(y = (-3).dp),
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
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
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