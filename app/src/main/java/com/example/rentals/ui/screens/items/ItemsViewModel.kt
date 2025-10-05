package com.example.rentals.ui.screens.items

import androidx.lifecycle.ViewModel
import com.example.rentals.data.RentalItem
import com.example.rentals.sampledata.sampleItemsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ItemsScreenUiState(val isLoading: Boolean, val items: List<RentalItem> = emptyList())

class ItemsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(value = ItemsScreenUiState(isLoading = true))

    val uiState: StateFlow<ItemsScreenUiState> = _uiState.asStateFlow()

    suspend fun getData() {
        delay(500)
        _uiState.update { it -> it.copy(items = sampleItemsData, isLoading = false) }
    }
}