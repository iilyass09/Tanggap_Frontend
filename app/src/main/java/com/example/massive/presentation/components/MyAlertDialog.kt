package com.example.massive.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Merah
import com.example.massive.ui.theme.poppins

@Composable
fun MyAlertDialog(
    shouldShowDialog: MutableState<Boolean>,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Batalkan Pengaduan?",
                    color = Color.Black,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Abu),
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        onClick = {
                            onCancel()
                            shouldShowDialog.value = false
                        }
                    ) {
                        Text(
                            text = "Tidak",
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Merah),
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        onClick = {
                            onConfirm()
                            shouldShowDialog.value = false
                        }
                    ) {
                        Text(
                            text = "Ya",
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    )
}