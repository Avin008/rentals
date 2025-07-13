package com.example.rentals.ui.components.ordercompletion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.viewmodels.BookedItem
import com.example.rentals.ui.viewmodels.CustomerInfo
import com.example.rentals.ui.viewmodels.EventDuration
import java.text.NumberFormat
import java.util.Locale
import kotlin.collections.forEach

@Composable
fun BookingDetailsCard(
    customerInfo: CustomerInfo,
    eventDuration: EventDuration,
    bookedItems: List<BookedItem>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CustomerInfoSection(customerInfo)
            EventDurationSection(eventDuration)
            BookedItemsSection(bookedItems)
        }
    }
}

@Composable
private fun CustomerInfoSection(customerInfo: CustomerInfo) {
    DetailSection(title = "CUSTOMER INFO") {
        Text(customerInfo.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
        Text(customerInfo.address, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(customerInfo.phone, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun EventDurationSection(duration: EventDuration) {
    DetailSection(title = "EVENT DURATION") {
        DurationRow(
            type = "Delivery",
            date = duration.deliveryDate
        )
        Spacer(modifier = Modifier.height(8.dp))
        DurationRow(
            type = "Pickup",
            date = duration.pickupDate
        )
    }
}

@Composable
private fun BookedItemsSection(bookedItems: List<BookedItem>) {
    val totalItems = bookedItems.sumOf { it.quantity }
    val totalAmount = bookedItems.sumOf { it.price * it.quantity }

    DetailSection(title = "BOOKED ITEMS ($totalItems)", showDivider = false) {
        bookedItems.forEach { item ->
            BookedItemRow(
                itemName = "${item.quantity}x ${item.name}",
                itemPrice = item.price
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 12.dp),
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )
    TotalAmountRow(totalAmount = totalAmount)
}



@Composable
private fun DetailSection(title: String, showDivider: Boolean = true, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        content()
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )
        }
    }
}

@Composable
private fun DurationRow(type: String, date: String) {
    Column {
        Text(text = type, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = date, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun BookedItemRow(itemName: String, itemPrice: Double) {

    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = itemName, style = MaterialTheme.typography.bodyLarge)
        Text(text = currencyFormatter.format(itemPrice), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun TotalAmountRow(totalAmount: Double) {

    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("TOTAL AMOUNT", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        Text(currencyFormatter.format(totalAmount), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
    }
}