package com.example.rentals.ui.screens.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.components.order.CustomerDetailsCard
import com.example.rentals.ui.components.order.DeliveryStatusCard
import com.example.rentals.ui.components.order.ItemsList
import com.example.rentals.ui.components.order.OrderSummaryCard
import com.example.rentals.ui.components.order.TopBar
import com.example.rentals.ui.theme.RentalsTheme

data class Customer(val name: String, val address: String, val phone: String)
data class OrderItem(val id: Int, val name: String, var quantity: Int, val price: Double)
data class Order(
    val id: String,
    val customer: Customer,
    val items: MutableList<OrderItem>,
    var deliveryStatus: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(order: Order) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar()

        LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { CustomerDetailsCard(customer = order.customer) }
                item {
                    DeliveryStatusCard(
                        status = order.deliveryStatus,
                        onUpdateStatus = { /* TODO */ })
                }
                item {
                    ItemsList(
                        items = order.items,
                        onRemoveItem = {},
                        onUpdateQuantity = { _, _ -> },
                        onAddNewItem = {})
                }
                item { OrderSummaryCard(items = order.items) }
            }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun OrderDetailScreenPreview() {
    val dummyOrder = Order(
        id = "12345",
        customer = Customer("John Doe", "123 Main St, Anytown, USA", "555-1234"),
        items = remember {
            mutableStateListOf(
                OrderItem(1, "Rake", 2, 15.0),
                OrderItem(2, "Shovel", 1, 20.0),
                OrderItem(3, "Gardening Gloves", 1, 10.0)
            )
        },
        deliveryStatus = "Pending"
    )
    RentalsTheme {
        OrderDetailsScreen(order = dummyOrder)
    }
}