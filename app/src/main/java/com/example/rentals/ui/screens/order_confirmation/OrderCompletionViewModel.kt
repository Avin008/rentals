package com.example.rentals.ui.screens.order_confirmation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class CustomerInfo(val name: String, val phone: String, val address: String)
data class EventDuration(val deliveryDate: String, val pickupDate: String)
data class BookedItem(val name: String, val quantity: Int, val price: Double)

data class OrderCompletionUiState(val isLoading: Boolean, val customerInfo: CustomerInfo, val eventDuration: EventDuration, val bookedItems: List<BookedItem>)

val customerInfo = CustomerInfo(
    name = "Ramesh Nag",
    phone = "+917759034372",
    address = "Vip Chowk, Badmal"
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

class OrderCompletionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderCompletionUiState(isLoading = true,customerInfo, eventDuration, bookedItems = emptyList()))
    val uiState: StateFlow<OrderCompletionUiState> = _uiState.asStateFlow()

    suspend fun getData() {
        delay(500)
        _uiState.value = OrderCompletionUiState(isLoading = false, customerInfo, eventDuration, bookedItems)

    }

}