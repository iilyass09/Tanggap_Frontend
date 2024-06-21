package com.example.massive.ui.screen.pengaduan

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.massive.R
import com.example.massive.data.storage.SharedPreferencesManager

@Composable
fun ImagePicker() {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    var imageUri: Any? by remember { mutableStateOf(sharedPreferencesManager.imageUri ?: R.drawable.picker) }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI : $uri")
            imageUri = uri
            sharedPreferencesManager.imageUri = uri.toString()
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    AsyncImage(
        modifier = Modifier
            .offset(x = 60.dp)
            .size(250.dp)
            .clickable {
                photoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        model = ImageRequest.Builder(context)
            .data(imageUri)
            .crossfade(true)
            .build(),
        contentDescription = "Avatar Image",
        contentScale = ContentScale.Crop,
    )
}
