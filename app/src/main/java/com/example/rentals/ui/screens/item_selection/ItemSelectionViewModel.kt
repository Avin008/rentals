package com.example.rentals.ui.screens.item_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentals.data.RentalItem
import com.example.rentals.sampledata.sampleItemsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ItemSelectionUiState(val items: List<RentalItem> = emptyList(), val isLoading: Boolean)


class ItemSelectionViewModel: ViewModel() {
        private val _uiState = MutableStateFlow(ItemSelectionUiState(isLoading = true))
        val uiState: StateFlow<ItemSelectionUiState> = _uiState.asStateFlow()

        fun getData() {
            viewModelScope.launch {
                delay(500)
                _uiState.update { it.copy(items = sampleItemsData, isLoading = false) }
            }
        }
}