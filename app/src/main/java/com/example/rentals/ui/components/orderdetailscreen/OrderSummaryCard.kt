package com.example.rentals.ui.components.orderdetailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.screens.OrderItem

@Composable
fun OrderSummaryCard(items: List<OrderItem>) {
    val subtotal = items.sumOf { it.price * it.quantity }
    val tax = subtotal * 0.1 // Example: 10% tax
    val total = subtotal + tax

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SectionHeader(title = "Order Summary", icon = Icons.Default.Receipt)
            Divider()
            SummaryRow("Subtotal", subtotal)
            SummaryRow("Tax (10%)", tax)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            SummaryRow("Total", total, isTotal = true)
        }
    }
}