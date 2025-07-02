package com.example.rentals.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentals.data.DeliveryItem
import com.example.rentals.sampledata.sampleOrderItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class HomeUiState(
    val deliveries: List<DeliveryItem> = emptyList(),
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
    val datesForPreview: List<LocalDate> = List(12) { index -> LocalDate.now().plusDays(index.toLong()) }
)

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.collect { state ->
                _uiState.update {
                    it.copy(deliveries =  dummyApiCall(state.selectedDate, state.selectedTabIndex), isLoading = false)
                }
            }
        }
    }

    private suspend fun dummyApiCall(selectedDate: LocalDate, tabIndex: Int): List<DeliveryItem> {
        delay(300)
        return sampleOrderItems.filter { orders -> orders.status == getStatus(tabIndex) }.filter { filteredItems -> filteredItems.deliveryDate == selectedDate.toString()}
    }

   private fun getStatus(tabIndex: Int): String {
        return when (tabIndex) {
            0 -> "delivery"
            1 -> "pickup"
            2 -> "ongoing"
            else -> "nothing"
        }
    }


    fun selectTab(selectedTabIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTabIndex = selectedTabIndex
            )
        }
    }

    fun changeDate(selectedDate: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(selectedDate = selectedDate)
        }
    }
}