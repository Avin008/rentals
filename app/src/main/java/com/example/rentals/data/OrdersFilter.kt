package com.example.rentals.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.ui.graphics.vector.ImageVector

sealed class OrdersFilters(val id: String,val label: String, val icon: ImageVector) {
    object Date: OrdersFilters(id = "1","Date", icon = Icons.Filled.KeyboardArrowDown)
    object Status: OrdersFilters(id= "2","Status", icon = Icons.Filled.KeyboardArrowDown)
    object Location: OrdersFilters(id = "3","Location", icon = Icons.Filled.KeyboardArrowDown)
}


val ordersFilters = listOf(OrdersFilters.Date, OrdersFilters.Status, OrdersFilters.Location)