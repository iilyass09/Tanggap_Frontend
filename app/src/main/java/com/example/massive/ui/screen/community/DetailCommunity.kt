package com.example.massive.ui.screen.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.massive.R
import com.example.massive.data.DataKomunitas
import com.example.massive.model.Komunitas
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Abu2
import com.example.massive.ui.theme.poppins

@Composable
fun DetailCommunity(
    navController: NavController,
    komunitassId : Int?
) {
    val newKomunitasList = DataKomunitas.ListKomunitas.filter { komunitas ->
        komunitas.id == komunitassId
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            DetailCommunityContent(
                navController = rememberNavController(),
                newKomunitasList = newKomunitasList
            )
            Spacer(modifier = Modifier.weight(1f))
            SendComment()
        }
    }
}

@Composable
fun DetailCommunityContent(
    navController: NavController,
    newKomunitasList : List<Komunitas>,
    modifier : Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier.padding(start = 10.dp, top = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = newKomunitasList[0].profil),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = newKomunitasList[0].nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = poppins
                    )
                    Text(
                        text = "20 menit yang lalu",
                        color = Abu,
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            newKomunitasList[0].bukti?.let { buktiId ->
                Image(
                    painter = painterResource(id = buktiId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .height(140.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = newKomunitasList[0].uraian,
                fontSize = 14.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 10.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.comment),
                            contentDescription = null
                        )
                        Text(text = "20", modifier = Modifier.padding(start = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = null
                        )
                        Text(text = "10", modifier = Modifier.padding(start = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Row(Modifier.padding(end = 5.dp),verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                        Text(text = "2", modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendComment() {
    var message by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Tulis Komentar...") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Abu2,
                cursorColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = { },
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
