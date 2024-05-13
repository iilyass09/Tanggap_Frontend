package com.example.massive.ui.screen.pengaduan

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.DataKategori
import com.example.massive.model.Kategori
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Biru2
import com.example.massive.ui.theme.componentsShapes
import com.example.massive.ui.theme.poppins

@Composable
fun PengaduanContentStep0(selectedCategory: Int, onCategorySelected: (Int) -> Unit, navController: NavController) {
    Column {
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            DataKategori.ListKategori.take(3).forEach { kategori ->
                Category(
                    kategori = kategori,
                    isSelected = kategori.id == selectedCategory,
                    onClick = { onCategorySelected(kategori.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            DataKategori.ListKategori.subList(3, 6).forEach { kategori ->
                Category(
                    kategori = kategori,
                    isSelected = kategori.id == selectedCategory,
                    onClick = { onCategorySelected(kategori.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            DataKategori.ListKategori.subList(6, 8).forEach { kategori ->
                Category(
                    kategori = kategori,
                    isSelected = kategori.id == selectedCategory,
                    onClick = { onCategorySelected(kategori.id) }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(115.dp))
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengaduanContentStep1(currentStep: MutableState<Int>, navController: NavController) {
    Column {
        // TEXTFIELD JUDUL
        val textJudul = remember { mutableStateOf("") }
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
        Spacer(modifier = Modifier.height(20.dp))

        // TEXTFIELD URAIAN
        val textUraian = remember { mutableStateOf("") }
        OutlinedTextField(
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
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
        Spacer(modifier = Modifier.height(320.dp))
        Button(
            onClick = { currentStep.value-- },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .background(brush = Brush.horizontalGradient(listOf(Color.LightGray, Color.LightGray))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Kembali",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun PengaduanContentStep2(currentStep: MutableState<Int>, navController: NavController) {
    Column {
        Button(
            onClick = { currentStep.value-- },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .background(brush = Brush.horizontalGradient(listOf(Abu2, Abu2))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Kembali",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins
                )
            }
        }
    }
}
@Composable
fun Category(kategori: Kategori, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Biru else Biru2, label = "")
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Biru, label = "")
    val clickableModifier = Modifier
        .clickable(onClick = onClick)

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .then(clickableModifier)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                imageVector = kategori.foto,
                contentDescription = null,
                tint = contentColor
            )
            Text(
                text = kategori.nama,
                fontFamily = poppins,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = contentColor
            )
        }
    }
}
@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isComplete = step < currentStep,
                isCurrent = step == currentStep,
            )
        }
    }
}
@Composable
fun Step(modifier: Modifier = Modifier, isComplete: Boolean, isCurrent: Boolean) {
    val color = if (isComplete || isCurrent) Biru else Color.LightGray
    val innerCircleColor = if (isComplete || isCurrent) Biru else Color.LightGray

    Box(
        modifier = modifier
    ) {
        Divider(
            modifier = Modifier.align(Alignment.Center),
            color = color,
            thickness = 5.dp
        )
        Canvas(modifier = Modifier
            .size(50.dp)
            .align(Alignment.Center),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
    }
}

@Composable
fun ClickableTextPanduan(onTextSelected : (String) -> Unit) {
    val initialText = "Panduan untuk melakukan pengaduan  "
    val registerText = "klik disini."

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Biru)){
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            fontFamily = poppins
        ),
        text = annotatedString, onClick = {offset ->

            annotatedString.getStringAnnotations(offset,offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent","{${span.item}}")

                    if (span.item == registerText) {
                        onTextSelected(span.item)
                    }
                }
        }
    )
}