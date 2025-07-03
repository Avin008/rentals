package com.example.rentals.ui.components.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Icon
import com.example.rentals.data.DeliveryItem
import com.example.rentals.sampledata.sampleOrderItems
import com.example.rentals.ui.theme.RentalsTheme

@Composable
fun SectionList(
    modifier: Modifier = Modifier,
    orders: List<DeliveryItem>,
    onClick: (String) -> Unit = {},
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    if (orders.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.LocalShipping,
                contentDescription = "No orders",
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No orders for today.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Create one to get started!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    } else {
        PullToRefresh(isRefreshing = isRefreshing, onRefresh = {
            onRefresh()
        }) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(
                    items = orders,
                    key = { order -> order.id }
                ) { order ->
                    DeliveryCard(
                        order = order,
                        onClick = { onClick(order.id) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    HorizontalDivider(
                        modifier = Modifier,
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Order List - With Items")
@Composable
fun OrderListPreviewWithItems() {
    RentalsTheme {
        SectionList(orders = sampleOrderItems, isRefreshing = true, onRefresh = {})
    }
}

@Preview(showBackground = true, name = "Order List - Empty")
@Composable
fun OrderListPreviewEmpty() {
    RentalsTheme {
        SectionList(orders = emptyList(), isRefreshing = false, onRefresh = {})
    }
}
