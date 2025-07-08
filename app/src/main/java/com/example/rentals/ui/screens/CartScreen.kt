package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rentals.ui.components.cart.CartTopBar
import com.example.rentals.ui.components.cart.OrderSummaryCard

@Composable
fun CartScreen() {
    Scaffold (modifier = Modifier.fillMaxSize(), topBar = { CartTopBar() }, bottomBar = {
        OrderSummaryCard(orderId = "", subtotal = 200.0, total = 200.0, hasAddress = true, onNavigateToCheckout = {})
    }){ innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
        }
    }
}