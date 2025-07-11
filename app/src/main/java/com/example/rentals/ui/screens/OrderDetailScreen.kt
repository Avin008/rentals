package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.theme.RentalsTheme

// Dummy data models for preview purposes
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
fun OrderDetailScreen(order: Order, onConfirmPickup: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(top = 10.dp),
                title = { Text("Order Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            OrderActionsSection(onConfirmPickup = onConfirmPickup)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { CustomerDetailsSection(customer = order.customer) }
            item { DeliveryStatusSection(status = order.deliveryStatus, onUpdateStatus = { /* TODO */ }) }
            item { ItemsListSection(items = order.items, onRemoveItem = {}, onUpdateQuantity = {_,_ ->}, onAddNewItem = {}) }
            item { OrderSummarySection(items = order.items) }
        }
    }
}

@Composable
fun CustomerDetailsSection(customer: Customer) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SectionHeader(title = "Customer Details", icon = Icons.Default.Person)
            Divider()
            DetailRow(label = "Name", value = customer.name)
            DetailRow(label = "Address", value = customer.address)
            DetailRow(label = "Phone", value = customer.phone)
        }
    }
}

@Composable
fun DeliveryStatusSection(status: String, onUpdateStatus: (String) -> Unit) {
    var currentStatus by remember { mutableStateOf(status) }
    Surface(modifier = Modifier.fillMaxWidth(),  color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SectionHeader(title = "Delivery Status", icon = Icons.Default.LocalShipping)
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                Text("Status: ", style = MaterialTheme.typography.titleMedium)
                Text(
                    currentStatus,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(onClick = { /* TODO: Implement status update logic */ }) {
                    Text("Update")
                }
            }
        }
    }
}

@Composable
fun ItemsListSection(
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
            SectionHeader(title = "Items", icon = Icons.Default.List)
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
                ItemRow(item = item, onRemove = { onRemoveItem(item) }, onQuantityChange = { newQty -> onUpdateQuantity(item, newQty) })
            }
        }
    }
}

@Composable
fun ItemRow(item: OrderItem, onRemove: () -> Unit, onQuantityChange: (Int) -> Unit) {
    var quantity by remember { mutableIntStateOf(item.quantity) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(item.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Text("Price: $${item.price}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = { if (quantity > 1) { quantity--; onQuantityChange(quantity) } }, modifier=Modifier.size(32.dp)) { Icon(Icons.Default.Remove, "Decrement") }
                Text("$quantity", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                IconButton(onClick = { quantity++; onQuantityChange(quantity) }, modifier=Modifier.size(32.dp)) { Icon(Icons.Default.Add, "Increment") }
            }
            IconButton(onClick = onRemove, modifier = Modifier.padding(start = 16.dp)) {
                Icon(Icons.Default.Delete, contentDescription = "Remove Item", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun OrderSummarySection(items: List<OrderItem>) {
    val subtotal = items.sumOf { it.price * it.quantity }
    val tax = subtotal * 0.1 // Example: 10% tax
    val total = subtotal + tax

    Surface(modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SectionHeader(title = "Order Summary", icon = Icons.Default.Receipt)
            Divider()
            SummaryRow("Subtotal", "$%.2f".format(subtotal))
            SummaryRow("Tax (10%)", "$%.2f".format(tax))
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            SummaryRow("Total", "$%.2f".format(total), isTotal = true)
        }
    }
}

@Composable
fun OrderActionsSection(onConfirmPickup: () -> Unit) {
    Surface(shadowElevation = 8.dp, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onConfirmPickup,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.Check, contentDescription = "Confirm Pickup", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Confirm Pickup & Quantities", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

// --- Helper Composables for clean UI ---

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun SummaryRow(label: String, value: String, isTotal: Boolean = false) {
    val textStyle = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge
    val fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = textStyle, fontWeight = fontWeight)
        Text(text = value, style = textStyle, fontWeight = fontWeight)
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
        OrderDetailScreen(order = dummyOrder, onConfirmPickup = {})
    }
}