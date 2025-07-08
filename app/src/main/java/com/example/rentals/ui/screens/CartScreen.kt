package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.components.cart.CartTopBar
import com.example.rentals.ui.components.cart.OrderSummaryCard
import com.example.rentals.ui.components.cart.RentalPeriodCard

@Composable
fun CartScreen() {
    Scaffold (modifier = Modifier.fillMaxSize(), topBar = { CartTopBar() }, bottomBar = {
        OrderSummaryCard(orderId = "", subtotal = 200.0, total = 200.0, hasAddress = true, onNavigateToCheckout = {})
    }){ innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            RentalPeriodCard(startDateMillis = System.currentTimeMillis(),
                endDateMillis = System.currentTimeMillis() + (3 * 24 * 60 * 60 * 1000L), // 3 days later
                deliveryHour = 10,
                deliveryMinute = 30,
                returnHour = 18,
                returnMinute = 0,
                rentalDurationDays = 3,
                formattedRentalDuration = "3 days, 7 Hours")
        }
    }
}