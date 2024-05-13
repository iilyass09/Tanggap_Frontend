package com.example.massive.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.massive.ui.navigation.Screen

data class NavigationItem (
    val title: String,
    val iconClick : Int,
    val iconUnclick : Int,
    val screen: Screen
)