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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.screens.OrderItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ItemCard(item: OrderItem, onRemove: () -> Unit, onQuantityChange: (Int) -> Unit) {

    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    }
    var quantity by remember { mutableIntStateOf(item.quantity) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(modifier = Modifier.size(64.dp), shape = MaterialTheme.shapes.medium) {
                Icon(Icons.Default.Image, contentDescription = item.name, modifier = Modifier.size(64.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("${currencyFormatter.format(item.price)} / day", style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { if (quantity > 1) quantity--; onQuantityChange(quantity) }, modifier = Modifier.size(32.dp)) { Icon(Icons.Default.Remove, "Decrement") }
                Text("$quantity", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                IconButton(onClick = { quantity++; onQuantityChange(quantity) }, modifier = Modifier.size(32.dp)) { Icon(Icons.Default.Add, "Increment") }
            }
            IconButton(onClick = onRemove, modifier = Modifier.padding(start = 16.dp)) {
                Icon(Icons.Default.Delete, contentDescription = "Remove Item", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}