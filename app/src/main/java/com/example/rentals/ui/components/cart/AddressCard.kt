package com.example.rentals.ui.components.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data class for type safety
data class Address(
    val name: String,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val phoneNumber: String
)

@Composable
fun AddressCard(
    address: Address?,
    onAddAddress: () -> Unit,
    onEditAddress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hasAddress = address != null

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Delivery Address",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (hasAddress) {
                    IconButton(
                        onClick = onEditAddress
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Address",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Content
            if (hasAddress && address != null) {
                AddressContent(address = address)
            } else {
                AddAddressButton(onClick = onAddAddress)
            }
        }
    }
}

@Composable
private fun AddressContent(address: Address) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Name
        Text(
            text = address.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Street Address
        Text(
            text = address.street,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        // City, State, ZIP
        Text(
            text = "${address.city}, ${address.state} ${address.zipCode}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phone Number
        Text(
            text = "Phone: ${address.phoneNumber}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AddAddressButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Add Delivery Address",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressCardWithAddressPreview() {
    MaterialTheme {
        val hardcodedAddress = Address(
            name = "Sarah Johnson",
            street = "456 Oak Avenue, Suite 12",
            city = "San Francisco",
            state = "CA",
            zipCode = "94102",
            phoneNumber = "+1 (415) 555-0123"
        )

        AddressCard(
            address = hardcodedAddress,
            onAddAddress = { },
            onEditAddress = { },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressCardWithoutAddressPreview() {
    MaterialTheme {
        AddressCard(
            address = null,
            onAddAddress = { },
            onEditAddress = { },
            modifier = Modifier.padding(16.dp)
        )
    }
}