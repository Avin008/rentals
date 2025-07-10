package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.sampledata.sampleItemsData
import com.example.rentals.ui.components.cart.Address
import com.example.rentals.ui.components.cart.AddressCard
import com.example.rentals.ui.components.cart.CartCard
import com.example.rentals.ui.components.cart.CartTopBar
import com.example.rentals.ui.components.cart.OrderSummaryCard
import com.example.rentals.ui.components.cart.RentalPeriodCard

@Composable
fun CartScreen(onProceedToCheckout: () -> Unit) {
    Scaffold (modifier = Modifier.fillMaxSize(), topBar = { CartTopBar() }, bottomBar = {
        OrderSummaryCard(orderId = "", subtotal = 200.0, total = 200.0, hasAddress = true, onNavigateToCheckout = {
            onProceedToCheckout()
        })
    }){ innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        RentalPeriodCard(
                            startDateMillis = System.currentTimeMillis(),
                            endDateMillis = System.currentTimeMillis() + (3 * 24 * 60 * 60 * 1000L), // 3 days later
                            deliveryHour = 10,
                            deliveryMinute = 30,
                            returnHour = 18,
                            returnMinute = 0,
                            rentalDurationDays = 3,
                            formattedRentalDuration = "3 days, 7 hours"
                        )

                        val hardcodedAddress = Address(
                            name = "Sarah Johnson",
                            street = "456 Oak Avenue, Suite 12",
                            city = "San Francisco",
                            state = "CA",
                            zipCode = "94102",
                            phoneNumber = "+1 (415) 555-0123"
                        )

                        AddressCard(
                            address = null,
                            onAddAddress = { },
                            onEditAddress = { },
                        )

                            Text(
                                text = "Items (${10})",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                            )

                    }

                }

                items(sampleItemsData) {
                    CartCard(it, 1, 3, {}, {}, {})
                }
            }
        }
    }
}