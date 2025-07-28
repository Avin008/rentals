package com.example.rentals.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(val title: String, val icon: ImageVector) {
    object Home: Destinations(title = "Home", icon = Icons.Default.Home)
    object Search: Destinations(title = "Search", icon = Icons.Default.Search)
    object Items: Destinations(title = "Items", icon = Icons.Default.ShoppingCart)
    object Notifications: Destinations(title = "Notifications", icon = Icons.Default.Notifications)
}

val bottomNavItems = listOf(Destinations.Home, Destinations.Search, Destinations.Items,
    Destinations.Notifications)