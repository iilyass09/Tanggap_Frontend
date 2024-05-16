package com.example.massive

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.massive.ui.screen.akun.AkunScreen
import com.example.massive.ui.screen.berita.BeritaScreen
import com.example.massive.ui.screen.berita.DetailBerita
import com.example.massive.ui.screen.chatbot.Chatbot2
import com.example.massive.ui.screen.chatbot.ChatbotScreen
import com.example.massive.ui.screen.community.CommunityScreen
import com.example.massive.ui.screen.home.HomeScreen
import com.example.massive.ui.navigation.NavigationItem
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.screen.login.LoginScreen
import com.example.massive.ui.screen.Onboarding1
import com.example.massive.ui.screen.Onboarding2
import com.example.massive.ui.screen.Onboarding3
import com.example.massive.ui.screen.Register
import com.example.massive.ui.screen.community.DetailCommunity
import com.example.massive.ui.screen.SplashScreen
import com.example.massive.ui.screen.pengaduan.Panduan
import com.example.massive.ui.screen.pengaduan.Pengaduan
import com.example.massive.ui.screen.pengaduan.Pengaduan2
import com.example.massive.ui.screen.pengaduan.Pengaduan3
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins
import com.example.massive.ui.utils.HideTopBar
import com.example.massive.ui.utils.ShowBottomBar
import com.example.massive.ui.utils.ShowFAB
import com.example.massive.ui.utils.ShowTopBarWithIcon

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun Massive(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AnimatedVisibility(visible = currentRoute.ShowFAB()
            ) {
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
        containerColor = Color.White,
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AnimatedVisibility(visible = currentRoute.HideTopBar()) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    title = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentRoute = navBackStackEntry?.destination?.route

                                AnimatedVisibility(
                                    visible = currentRoute.ShowTopBarWithIcon()
                                ) {
                                    IconButton(
                                        onClick = { navController.popBackStack() }
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(top = 5.dp)
                                                .offset(x = (-5).dp),
                                            imageVector = Icons.Default.ArrowBackIosNew,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(x = (-10).dp),
                                text = getTitleForRoute(route = currentRoute),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AnimatedVisibility(visible = currentRoute.ShowBottomBar()) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { contentPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Screen.Splash.route){ SplashScreen(navController) }
            composable(Screen.Onboarding1.route){ Onboarding1(navController) }
            composable(Screen.Onboarding2.route){ Onboarding2(navController) }
            composable(Screen.Onboarding3.route){ Onboarding3(navController) }
            composable(Screen.Login.route){ LoginScreen(navController) }
            composable(Screen.Register.route){ Register(navController) }
            composable(Screen.Home.route){ HomeScreen(navController) }
            composable(Screen.Community.route){ CommunityScreen(navController) }
            composable(Screen.Pengaduan.route) { Pengaduan(navController) }
            composable(Screen.Pengaduan2.route) { Pengaduan2(navController) }
            composable(Screen.Pengaduan3.route) { Pengaduan3(navController) }
            composable(Screen.Berita.route){ BeritaScreen(navController) }
            composable(Screen.Akun.route){ AkunScreen(navController) }
            composable(Screen.Chatbot.route){ ChatbotScreen(navController) }
            composable(Screen.Chatbot2.route){ Chatbot2(navController) }
            composable(Screen.Panduan.route){ Panduan(navController) }
            composable(
                Screen.DetailBerita.route + "/{beritaId}",
                arguments = listOf(navArgument("beritaId") { type = NavType.IntType})
            ) { navBackStackEntry ->
                DetailBerita(navController = navController, beritasId = navBackStackEntry.arguments?.getInt("beritaId"))
            }
            composable(
                Screen.DetailCommunity.route + "/{komunitasId}",
                arguments = listOf(navArgument("komunitasId") { type = NavType.IntType})
            ) { navBackStackEntry ->
                DetailCommunity(navController = navController, komunitassId = navBackStackEntry.arguments?.getInt("komunitasId"))
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                iconClick = R.drawable.iconhomeclick,
                iconUnclick = R.drawable.home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_komunitas),
                iconClick = R.drawable.iconkomunitasclick,
                iconUnclick = R.drawable.iconkomunitas,
                screen = Screen.Community
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_pengaduan),
                iconClick = R.drawable.iconpengaduanclick,
                iconUnclick = R.drawable.iconpengaduan,
                screen = Screen.Pengaduan
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_berita),
                iconClick = R.drawable.iconberitaclick,
                iconUnclick = R.drawable.iconberita,
                screen = Screen.Berita
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_akun),
                iconClick = R.drawable.iconakunclick,
                iconUnclick = R.drawable.iconakun,
                screen = Screen.Akun
            ),
        )

        navigationItems.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                ) {
                    val icon = if (currentRoute == item.screen.route) item.iconClick else item.iconUnclick
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.screen.route) Biru else Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    modifier = Modifier.offset(y = (-5).dp),
                    text = item.title,
                    fontFamily = poppins,
                    fontSize = 11.sp,
                    color = if (currentRoute == item.screen.route) Biru else Color.Black
                )
            }
        }
    }
}

@Composable
fun getTitleForRoute(route: String?): String {
    return when (route) {
        Screen.Community.route -> stringResource(id = R.string.menu_komunitas)
        Screen.Pengaduan.route -> stringResource(id = R.string.menu_pengaduan)
        Screen.Pengaduan2.route -> stringResource(id = R.string.menu_pengaduan)
        Screen.Pengaduan3.route -> stringResource(id = R.string.menu_pengaduan)
        Screen.Berita.route -> stringResource(id = R.string.menu_berita)
        Screen.Akun.route -> stringResource(id = R.string.menu_akun)
        Screen.Chatbot.route -> stringResource(id = R.string.menu_chatbot)
        Screen.Panduan.route -> stringResource(id = R.string.menu_panduan)
        else -> {
            if (route?.startsWith(Screen.DetailBerita.route) == true) {
                stringResource(id = R.string.menu_berita)
            } else if (route?.startsWith(Screen.DetailCommunity.route) == true) {
                stringResource(id = R.string.menu_komunitas)
            } else {
                stringResource(id = R.string.salah)
            }
        }
    }
}
