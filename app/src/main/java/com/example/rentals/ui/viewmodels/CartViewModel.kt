package com.example.rentals.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentals.data.RentalItem
import com.example.rentals.sampledata.sampleItemsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<List<RentalItem>>(emptyList())
    val uiState: StateFlow<List<RentalItem>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = sampleItemsData
        }
    }
}