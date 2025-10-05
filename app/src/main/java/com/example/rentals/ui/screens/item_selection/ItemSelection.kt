package com.example.rentals.ui.screens.item_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.navigation.Cart
import com.example.rentals.ui.components.item_selection.CartSummary
import com.example.rentals.ui.components.item_selection.FilterSection
import com.example.rentals.ui.components.item_selection.ItemList
import com.example.rentals.ui.components.shared.CustomSearchBar

@Composable
fun ItemSelectionScreen(itemSelectionViewModel: ItemSelectionViewModel = viewModel(), backStack: SnapshotStateList<Any>) {

    val itemSelectionUiState by itemSelectionViewModel.uiState.collectAsStateWithLifecycle()
    val text = rememberTextFieldState("")

    LaunchedEffect(Unit) {
        itemSelectionViewModel.getData()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        if(!itemSelectionUiState.isLoading){
            CustomSearchBar(label = "Search Items",textFieldState = text, onSearch = {}, searchResults = emptyList())
        } }, bottomBar = {
        if (!itemSelectionUiState.isLoading) { CartSummary(10, totalPrice = 100.00, onContinueClick = {
            backStack.add(Cart(deliveryDate = "", deliveryTime = "", pickupDate = "", pickupTime = ""))
        }) }
    }) {innerPadding ->
        if (itemSelectionUiState.isLoading) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                Spacer(modifier = Modifier.height(10.dp))
                FilterSection()
                Spacer(modifier = Modifier.height(10.dp))
                ItemList(filteredItems = itemSelectionUiState.items)
            }
        }
    }
}