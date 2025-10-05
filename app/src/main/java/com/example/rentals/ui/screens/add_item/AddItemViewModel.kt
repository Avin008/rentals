package com.example.rentals.ui.screens.add_item

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddItemUiState(
    val name: String = "",
    val category: String = "",
    val price: String = "",
    val description: String = "",
    val inStock: String = "",
    val totalItems: String = "",
    val imageUri: String? = null,
    val isFormValid: Boolean = false
)

class AddItemViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddItemUiState())
    val uiState: StateFlow<AddItemUiState> = _uiState.asStateFlow()

    val categories = listOf("Bicycles", "Electronics", "Kitchenware", "Outdoor", "Furniture", "Decor")

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
        validateForm()
    }

    fun updateCategory(category: String) {
        _uiState.update { it.copy(category = category) }
        validateForm()
    }

    fun updatePrice(price: String) {
        _uiState.update { it.copy(price = price) }
        validateForm()
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
        validateForm()
    }

    fun updateInStock(inStock: String) {
        _uiState.update { it.copy(inStock = inStock) }
        validateForm()
    }

    fun updateTotalItems(totalItems: String) {
        _uiState.update { it.copy(totalItems = totalItems) }
        validateForm()
    }

    fun updateImageUri(uri: String?) {
        _uiState.update { it.copy(imageUri = uri) }
        validateForm()
    }

    private fun validateForm() {
        val state = _uiState.value
        val isValid = state.name.isNotBlank() &&
                state.category.isNotBlank() &&
                state.price.isNotBlank() && state.price.toDoubleOrNull() != null &&
                state.description.isNotBlank() &&
                state.inStock.isNotBlank() && state.inStock.toIntOrNull() != null &&
                state.totalItems.isNotBlank() && state.totalItems.toIntOrNull() != null &&
                state.imageUri != null
        _uiState.update { it.copy(isFormValid = isValid) }
    }

    fun saveItem() {
        if (_uiState.value.isFormValid) {
            // TODO: Implement item saving logic (e.g., to a repository or database)
            println("Item saved: ${_uiState.value}")
        }
    }
}
