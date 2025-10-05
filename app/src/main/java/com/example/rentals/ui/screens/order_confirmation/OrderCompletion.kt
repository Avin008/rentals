package com.example.rentals.ui.screens.order_confirmation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.ui.components.order_completion.ActionButtonsBar
import com.example.rentals.ui.components.order_completion.BookingDetailsCard
import com.example.rentals.ui.components.order_completion.ConfirmationHeader

@Composable
fun OrderCompletionScreen(onNavigateToHome: () -> Unit, viewModel: OrderCompletionViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getData()
    }

    if(uiState.isLoading) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    }else {
        Scaffold(
            bottomBar = {
                ActionButtonsBar(onViewDetailsClick = {onNavigateToHome()}, onNavigateToHome)
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
                    ConfirmationHeader(label = "Booking Confirmed!", description = "Customer has been notified via WhatsApp.")
                }
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                item {
                    BookingDetailsCard(
                        customerInfo = uiState.customerInfo,
                        eventDuration = uiState.eventDuration,
                        bookedItems = uiState.bookedItems
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
