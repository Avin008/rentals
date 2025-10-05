package com.example.rentals.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentals.data.model.DeliveryItem
import com.example.rentals.sampledata.sampleOrderItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchUiState(val isLoading: Boolean, val items: List<DeliveryItem> = emptyList())

class SearchViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState(isLoading = true))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(items = sampleOrderItems, isLoading = false) }
        }
    }
}