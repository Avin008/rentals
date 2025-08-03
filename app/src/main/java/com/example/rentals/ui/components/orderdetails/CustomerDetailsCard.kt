package com.example.rentals.ui.components.orderdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.screens.Customer

@Composable
fun CustomerDetailsCard(customer: Customer) {
    Surface(
        modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceContainer,
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