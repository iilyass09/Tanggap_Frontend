package com.example.massive.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.massive.navigation.Screen

data class NavigationItem (
    val title: String,
    val iconClick : Int,
    val iconUnclick : Int,
    val screen: Screen
)