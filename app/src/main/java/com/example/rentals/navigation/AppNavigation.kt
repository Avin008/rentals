package com.example.rentals.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.rentals.ui.screens.CartScreen
import com.example.rentals.ui.screens.HomeScreen
import com.example.rentals.ui.screens.ItemSelectionScreen
import kotlinx.serialization.Serializable

@Serializable
data object Home: NavKey

@Serializable
data class ItemSelection(val deliveryDate: String, val deliveryTime: String, val pickupDate: String, val pickupTime: String): NavKey

@Serializable
data class Cart(val deliveryDate: String, val pickupDate: String, val deliveryTime: String, val pickupTime: String): NavKey

@Composable
fun AppNavigation(modifier: Modifier, backStack:  SnapshotStateList<Any>) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    HomeScreen(backStack = backStack)
                }
                is ItemSelection -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    ItemSelectionScreen(backStack = backStack)
                }
                is Cart -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    CartScreen()
                }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}