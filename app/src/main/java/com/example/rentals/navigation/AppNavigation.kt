package com.example.rentals.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.rentals.ui.screens.add_item.AddItem
import com.example.rentals.ui.screens.cart.CartScreen
import com.example.rentals.ui.screens.order.Customer
import com.example.rentals.ui.screens.home.HomeScreen
import com.example.rentals.ui.screens.item_selection.ItemSelectionScreen
import com.example.rentals.ui.screens.items.ItemsScreen
import com.example.rentals.ui.screens.notifications.NotificationsScreen
import com.example.rentals.ui.screens.order.Order
import com.example.rentals.ui.screens.order_confirmation.OrderCompletionScreen
import com.example.rentals.ui.screens.order.OrderDetailsScreen
import com.example.rentals.ui.screens.order.OrderItem
import com.example.rentals.ui.screens.profile.Profile
import com.example.rentals.ui.screens.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object Home: NavKey

@Serializable
data class ItemSelection(val deliveryDate: String, val deliveryTime: String, val pickupDate: String, val pickupTime: String): NavKey

@Serializable
data class Cart(val deliveryDate: String, val pickupDate: String, val deliveryTime: String, val pickupTime: String): NavKey


@Serializable
data class OrderCompletion(val orderId: String): NavKey

@Serializable
data class OrderDetails(val orderId: String): NavKey

@Serializable
data class EditItem(val itemId: String): NavKey

@Serializable
data object Search: NavKey

@Serializable
data object Items: NavKey

@Serializable
data object Notifications: NavKey

@Serializable
data object AddItem: NavKey

@Serializable
data object Profile: NavKey

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
                is Search -> NavEntry(key) {
                    SearchScreen()
                }
                is Items -> NavEntry(key) {
                    ItemsScreen(backStack = backStack)
                }
                is Notifications -> NavEntry(key) {
                    NotificationsScreen()
                }
                is ItemSelection -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    ItemSelectionScreen(backStack = backStack)
                }
                is Cart -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    CartScreen(onProceedToCheckout = { backStack.add(OrderCompletion(orderId = "12345")) })
                }
                is Profile -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    Profile()
                }
                is OrderCompletion -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    OrderCompletionScreen(onNavigateToHome = {
                        backStack.clear()
                        backStack.add(Home)
                    })
                }

                is AddItem -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {
                    AddItem()
                }

                is OrderDetails -> NavEntry(key, metadata = mapOf("extraDataKey" to "extraDataValue")) {

                    val dummyOrder = Order(
                        id = "12345",
                        customer = Customer("Jane Doe", "456 Oak Ave, Someplace, USA", "555-5678"),
                        items = listOf(
                            OrderItem(1, "Vintage Armchair", 1, 150.0),
                            OrderItem(2, "Wooden Coffee Table", 1, 75.50),
                            OrderItem(3, "Floor Lamp", 2, 45.0)
                        ) as MutableList<OrderItem>,
                        deliveryStatus = "Out for Delivery"
                    )

                    OrderDetailsScreen(order = dummyOrder)
                }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}