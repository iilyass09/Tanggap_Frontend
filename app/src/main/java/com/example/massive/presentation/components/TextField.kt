package com.example.massive.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.massive.data.models.ChatRequest
import com.example.massive.data.models.ChatResponse
import com.example.massive.data.storage.RetrofitInstance
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.componentsShapes
import com.example.massive.ui.theme.poppins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Login-Register
@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        shape = MaterialTheme.shapes.medium,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        shape = MaterialTheme.shapes.medium,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password Icon"
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Visibility Icon"
            } else {
                "Visibility Off Icon"
            }

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    )
}

//Forgot Password
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(labelValue: String) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentsShapes.small),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Biru,
            focusedLabelColor = Biru,
            cursorColor = Biru,
        ),
        keyboardActions = KeyboardActions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPTextField(
    otpLength: Int = 5,
    onOtpComplete: (String) -> Unit
) {
    val otpValues = remember { mutableStateListOf(*Array(otpLength) { "" }) }
    val focusManager = LocalFocusManager.current
    val inputFocusRequester = List(otpLength) { FocusRequester() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        for (index in 0 until otpLength) {
            OutlinedTextField(
                shape = RoundedCornerShape(20),
                value = otpValues[index],
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp)
                    .focusRequester(inputFocusRequester[index]),
                label = { Text(text = "") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Biru,
                    focusedLabelColor = Biru,
                    cursorColor = Biru,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        otpValues[index] = newValue
                        if (newValue.isNotEmpty()) {
                            if (index < otpLength - 1) {
                                inputFocusRequester[index + 1].requestFocus()
                            } else {
                                focusManager.clearFocus()
                                onOtpComplete(otpValues.joinToString(""))
                            }
                        }
                    }
                },
            )
        }
    }
}


@Composable
fun ButtonPerbarui(value: String) {
    Button(
        onClick = { },
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
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppins
            )
        }

    }
}

@Composable
fun ClickableEmailTextComponent(onTextSelected : (String) -> Unit) {
    val initialText = "Belum dapat email?  "
    val registerText = "Kirim ulang email"

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
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
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

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotTextField(chatMessages: MutableList<Pair<String, Boolean>>) {
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        androidx.compose.material3.TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Kirim pesan...") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Abu2,
                cursorColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (message.isNotEmpty()) {
                            chatMessages.add(Pair(message, true))
                            val api = RetrofitInstance.chatbotApi
                            val call = api.sendMessage(ChatRequest(message))
                            call.enqueue(object : Callback<ChatResponse> {
                                override fun onResponse(
                                    call: Call<ChatResponse>,
                                    response: Response<ChatResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        response.body()?.let {
                                            chatMessages.add(Pair(it.response, false))
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Failed to get response",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                                    Toast.makeText(
                                        context,
                                        "Error: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                            message = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send message"
                    )
                }
            }
        )
    }
}