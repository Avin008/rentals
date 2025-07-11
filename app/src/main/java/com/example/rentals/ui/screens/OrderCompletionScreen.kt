package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OrderCompletionScreen(onNavigateToHome: () -> Unit) {
    // In a real app, this data would come from a ViewModel
    val customerInfo = CustomerInfo(
        name = "Jane Doe",
        phone = "+1 (555) 123-4567",
        address = "123 Main St, Anytown, USA 12345"
    )
    val eventDuration = EventDuration(
        deliveryDate = "July 12, 2025, 10:00 AM",
        pickupDate = "July 13, 2025, 5:00 PM"
    )
    val bookedItems = listOf(
        BookedItem(name = "Large Tents", quantity = 2, price = 150.0),
        BookedItem(name = "Folding Chairs", quantity = 10, price = 50.0),
        BookedItem(name = "Bluetooth Speaker", quantity = 1, price = 25.0),
    )

    Scaffold(
        bottomBar = {
            ActionButtons(
                onViewDetailsClick = { onNavigateToHome() },
                onShareReceiptClick = { /* Handle Share Receipt */ }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConfirmationHeader()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                BookingDetailsCard(
                    customerInfo = customerInfo,
                    eventDuration = eventDuration,
                    bookedItems = bookedItems
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ConfirmationHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(48.dp))
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Success",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Booking Confirmed!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "The client has been notified via WhatsApp.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

// Data classes to model the screen state
data class CustomerInfo(val name: String, val phone: String, val address: String)
data class EventDuration(val deliveryDate: String, val pickupDate: String)
data class BookedItem(val name: String, val quantity: Int, val price: Double)

@Composable
private fun BookingDetailsCard(
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
        Text(customerInfo.phone, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(customerInfo.address, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
    Divider(modifier = Modifier.padding(vertical = 12.dp))
    TotalAmountRow(totalAmount = totalAmount)
}

@Composable
private fun ActionButtons(onViewDetailsClick: () -> Unit, onShareReceiptClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onViewDetailsClick,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("VIEW ORDER DETAILS", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = onShareReceiptClick,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("SHARE RECEIPT", fontSize = 16.sp)
        }
    }
}

@Composable
private fun DetailSection(title: String, showDivider: Boolean = true, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        content()
        if (showDivider) {
            Divider(modifier = Modifier.padding(vertical = 12.dp))
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