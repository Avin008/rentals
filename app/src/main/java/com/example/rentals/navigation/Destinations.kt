package com.example.rentals.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(val title: String, val icon: ImageVector, val route: Any) {
    object Home: Destinations(title = "Home", icon = Icons.Default.Home, route = com.example.rentals.navigation.Home)
    object Search: Destinations(title = "Search", icon = Icons.Default.Search, route = com.example.rentals.navigation.Search)
    object Items: Destinations(title = "Items", icon = Icons.Default.ShoppingCart, route = com.example.rentals.navigation.Items)
    object Notifications: Destinations(title = "Notifications", icon = Icons.Default.Notifications, route = com.example.rentals.navigation.Notifications)

}

val bottomNavItems = listOf(Destinations.Home, Destinations.Search, Destinations.Items,
    Destinations.Notifications)