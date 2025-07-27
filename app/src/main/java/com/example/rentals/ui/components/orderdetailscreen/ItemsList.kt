package com.example.rentals.ui.components.orderdetailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.screens.OrderItem
import kotlin.collections.forEach

@Composable
fun ItemsList(
    items: List<OrderItem>,
    onRemoveItem: (OrderItem) -> Unit,
    onUpdateQuantity: (OrderItem, Int) -> Unit,
    onAddNewItem: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Items (10)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            OutlinedButton(onClick = onAddNewItem) {
                Icon(Icons.Default.Add, contentDescription = "Add Item", modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Item")
            }
        }
        if (items.isEmpty()) {
            Text(
                "No items in this order.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
            )
        } else {
            items.forEach { item ->
                ItemCard(item = item, onRemove = { onRemoveItem(item) }, onQuantityChange = { newQty -> onUpdateQuantity(item, newQty) })
            }
        }
    }
}