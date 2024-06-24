package com.example.massive.presentation.screen.fpassword

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.presentation.components.ClickableEmailTextComponent
import com.example.massive.presentation.components.OTPTextField
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun FPassword2(navController: NavController) {
    val context = LocalContext.current.applicationContext
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column() {
            Text(
                text = "Periksa Email Anda",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Kami mengirimkan link reset ke tanggap@gmail.com masukkan 5 digit kode yang disebutkan dalam email",
                color = Abu,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppins,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            OTPTextField(onOtpComplete = { otp ->
                println("Entered OTP: $otp")
            })
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navController.navigate(Screen.FPassword3.route) },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Biru, Biru))
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Verifikasi Kode",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))
            ClickableEmailTextComponent(onTextSelected = {
                Toast.makeText(context, "Email telah dikirim",  Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Preview
@Composable
fun FPassword2Prev() {
    FPassword2(navController = rememberNavController())
}