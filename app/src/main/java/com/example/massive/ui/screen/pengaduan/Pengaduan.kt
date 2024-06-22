package com.example.massive.ui.screen.pengaduan

import android.annotation.SuppressLint
    import android.text.TextPaint
    import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
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
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.ClickableText
    import androidx.compose.foundation.text.KeyboardActions
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.Divider
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextFieldDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
    import androidx.compose.ui.graphics.nativeCanvas
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.text.SpanStyle
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.buildAnnotatedString
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.text.withStyle
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import com.example.massive.data.storage.SharedPreferencesManager
    import com.example.massive.ui.navigation.Screen
    import com.example.massive.ui.theme.Biru
    import com.example.massive.ui.theme.componentsShapes
    import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pengaduan(navController: NavController, viewModel: PengaduanViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val currentStep = remember { mutableStateOf(0) }
    val textJudul = remember { mutableStateOf("") }
    val textUraian = remember { mutableStateOf("") }
    val textLokasi = remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userId = sharedPreferencesManager.userId

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
                currentStep = 0
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(start = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(85.dp)
            ) {
                Text(
                    text = "Isi Form",
                    fontSize = 14.sp,
                    color = Biru
                )
                Text(
                    text = "Bukti",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier.offset(x = (-7).dp),
                    text = "Pratinjau",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Isi form pengaduan anda.",
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            ClickableTextPanduan(onTextSelected = { navController.navigate(Screen.Panduan.route) })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Judul Pengaduan") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textJudul.value,
                onValueChange = {
                    textJudul.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(5),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(componentsShapes.small),
                label = { Text(text = "Uraian Pengaduan") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textUraian.value,
                onValueChange = {
                    textUraian.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(componentsShapes.small),
                label = { Text(text = "Lokasi Pengaduan") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardActions = KeyboardActions.Default,
                value = textLokasi.value,
                onValueChange = {
                    textLokasi.value = it
                },
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (userId != -1) {
                        if (textJudul.value.isEmpty() || textUraian.value.isEmpty() || textLokasi.value.isEmpty()) {
                            Log.e("Pengaduan", "Field tidak boleh kosong")
                            return@Button
                        }

                        // Save values to shared preferences
                        sharedPreferencesManager.judul = textJudul.value
                        sharedPreferencesManager.uraian = textUraian.value
                        sharedPreferencesManager.lokasi = textLokasi.value
                        navController.navigate(Screen.Pengaduan2.route)

                    } else {
                        Log.e("Pengaduan", "User ID tidak ditemukan")
                    }
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
                        text = "Selanjutnya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}

@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0 until numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isComplete = step < currentStep,
                isCurrent = step == currentStep,
                stepNumber = step + 1
            )
        }
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isComplete: Boolean, isCurrent: Boolean, stepNumber: Int) {
    val colors = if (isComplete || isCurrent) Biru else Color.LightGray
    val innerCircleColor = if (isComplete || isCurrent) Biru else Color.LightGray

    Box(
        modifier = modifier
    ) {
        Divider(
            modifier = Modifier.align(Alignment.Center),
            color = colors,
            thickness = 5.dp
        )
        Canvas(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center),
            onDraw = {
                drawCircle(color = innerCircleColor)
                drawIntoCanvas { canvas ->
                    val textPaint = TextPaint().apply {
                        this.color = android.graphics.Color.WHITE
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 15.sp.toPx()
                        isFakeBoldText = true
                    }
                    canvas.nativeCanvas.drawText(
                        stepNumber.toString(),
                        size.width / 2,
                        (size.height / 2) - ((textPaint.descent() + textPaint.ascent()) / 2),
                        textPaint
                    )
                }
            }
        )
    }
}

@Composable
fun ClickableTextPanduan(onTextSelected: (String) -> Unit) {
    val annotatedText = buildAnnotatedString {
        append("Panduan untuk melakukan pengaduan ")
        pushStringAnnotation(tag = "Klik disini", annotation = "Klik disini")
        withStyle(
            style = SpanStyle(
                color = Biru,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append("Klik disini")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "Klik disini", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    onTextSelected(annotation.item)
                }
        },
        style = TextStyle(
            fontFamily = poppins,
            fontSize = 12.sp
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
