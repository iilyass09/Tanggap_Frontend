package com.example.massive

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.massive.navigation.*
import com.example.massive.screen.*
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.Gray2
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Massive(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        floatingActionButton = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute != Screen.Chatbot.route) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Chatbot.route) },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp),
                    containerColor = Biru
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.iconbot2),
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Gray2,
        topBar = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute == Screen.Home.route) {
                TopAppBar(
                    title = {
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "Muhammad Aziz",
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Cibeunying Kaler",
                                fontWeight = FontWeight.Normal,
                                fontFamily = poppins,
                                fontSize = 12.sp
                            )
                        }
                    },
                    navigationIcon = {
                        Box{
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(7.dp)
                                    .size(40.dp)
                                    .clickable {  }
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Screen.Chatbot.route) }) {
                            Image(
                                painter = painterResource(id = R.drawable.iconbot),
                                contentDescription = "Chatbot",
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .size(40.dp)
                            )
                        }
                        IconButton(onClick = {  }) {
                            Image(
                                painter = painterResource(id = R.drawable.notif),
                                contentDescription = "Notif",
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .size(23.dp)
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute !in listOf(Screen.Chatbot.route))
                BottomBar(navController)
        },
        modifier = modifier
    ) { contentPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route){ HomeScreen(navController) }
            composable(Screen.Community.route){ CommunityScreen(navController) }
            composable(Screen.Pengaduan.route){ PengaduanScreen(navController) }
            composable(Screen.Berita.route){ BeritaScreen(navController) }
            composable(Screen.Akun.route){ AkunScreen(navController) }
            composable(Screen.Chatbot.route){ ChatbotScreen(navController) }
        }
    }
}

@Composable
fun BottomBar(
    navController : NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(title = stringResource(id = R.string.menu_home), icon = Icons.Default.Home, screen = Screen.Home),
            NavigationItem(title = stringResource(id = R.string.menu_komunitas), icon = Icons.Default.Face, screen = Screen.Community),
            NavigationItem(title = stringResource(id = R.string.menu_pengaduan), icon = Icons.Default.AccountCircle, screen = Screen.Pengaduan),
            NavigationItem(title = stringResource(id = R.string.menu_berita), icon = Icons.Default.List, screen = Screen.Berita),
            NavigationItem(title = stringResource(id = R.string.menu_akun), icon = Icons.Default.AccountCircle, screen = Screen.Akun),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){ saveState = true }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
