package com.example.massive.presentation.screen.akun

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.contentColorFor
import coil.compose.rememberImagePainter
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.MyAlertDialog
import com.example.massive.presentation.screen.pengaduan.PengaduanViewModel
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Merah
import com.example.massive.ui.theme.poppins

@Composable
fun PengaduanSaya() {
    val context = LocalContext.current
    val aduanViewModel : PengaduanViewModel = viewModel()
    val aduanList by aduanViewModel.aduanList.observeAsState(emptyList())
    val shouldShowDialog = remember { mutableStateOf(false) }
    val idToDelete = remember { mutableStateOf<Int?>(null) }
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userId = sharedPreferencesManager.userId
    val token = sharedPreferencesManager.authToken ?: return

    LaunchedEffect(Unit) {
        aduanViewModel.fetchAduanList(userId.toString(), token)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 10.dp)
        ) {
            items(aduanList) { aduan ->
                Surface(
                    color = contentColorFor(backgroundColor = Color.White),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, color = Abu2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 5.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.HourglassTop,
                                contentDescription = null,
                                tint = Biru
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Status : ${aduan.status}",
                                fontFamily = poppins,
                                fontSize = 12.sp,
                                color = Biru
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = aduan.createdAt.substring(0, 10),
                                fontFamily = poppins,
                                fontSize = 10.sp,
                                color = Abu
                            )
                        }
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clipToBounds()
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = aduan.foto),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.matchParentSize()
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = aduan.judul,
                                    fontFamily = poppins,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = aduan.uraian,
                                    fontFamily = poppins,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    maxLines = 5,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Button(
                            onClick = {
                                shouldShowDialog.value = true
                                idToDelete.value = aduan.id
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(40.dp),
                            contentPadding = PaddingValues(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(40.dp)
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                Merah,
                                                Merah
                                            )
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Batalkan Pengaduan",
                                    fontSize = 14.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        if (shouldShowDialog.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
        }

        if (shouldShowDialog.value) {
            MyAlertDialog(
                shouldShowDialog = shouldShowDialog,
                onConfirm = {
                    idToDelete.value?.let { id ->
                        aduanViewModel.deleteAduan(id, token)
                        shouldShowDialog.value = false
                    }
                },
                onCancel = {
                    shouldShowDialog.value = false // Close dialog
                }
            )
        }
    }
    val deletionResult by aduanViewModel.deletionResult.observeAsState()
    deletionResult?.let { result ->
        result.onSuccess {
            Toast.makeText(context, "Pengaduan berhasil dibatalkan", Toast.LENGTH_LONG).show()
        }.onFailure {
            Toast.makeText(context, "Gagal membatalkan pengaduan", Toast.LENGTH_LONG).show()
        }
    }
}



