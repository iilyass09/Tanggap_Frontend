package com.example.massive.ui.screen.pengaduan

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.data.DataKategori
import com.example.massive.model.Kategori
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Biru2
import com.example.massive.ui.theme.poppins

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pengaduan(navController: NavController) {
    val currentStep = remember { mutableStateOf(0) }
    var selectedCategory by remember { mutableStateOf(0) }
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
                numberOfSteps = 2,
                currentStep = currentStep.value
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                Text(
                    text = "Pilih Kategori",
                    fontSize = 14.sp,
                    color = Biru
                )
                Text(
                    text = "Isi Form",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier
                        .offset(x = 18.dp),
                    text = "Pratinjau",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Pilih kategori pengaduan anda",
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            ClickableTextPanduan(onTextSelected = { navController.navigate(Screen.Panduan.route) })
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                DataKategori.ListKategori.take(3).forEach { kategori ->
                    Category(
                        kategori = kategori,
                        isSelected = kategori.id == selectedCategory,
                        onClick = { selectedCategory = kategori.id }
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            // Second row with items 4 to 6
            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                DataKategori.ListKategori.subList(3, 6).forEach { kategori ->
                    Category(
                        kategori = kategori,
                        isSelected = kategori.id == selectedCategory,
                        onClick = { selectedCategory = kategori.id }
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                DataKategori.ListKategori.subList(6, 8).forEach { kategori ->
                    Category(
                        kategori = kategori,
                        isSelected = kategori.id == selectedCategory,
                        onClick = { selectedCategory = kategori.id }
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { navController.navigate(Screen.Pengaduan2.route) },
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
                        fontWeight = FontWeight.Bold,
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
fun ClickableTextPanduan(onTextSelected : (String) -> Unit) {
    val initialText = "Panduan untuk melakukan pengaduan "
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

