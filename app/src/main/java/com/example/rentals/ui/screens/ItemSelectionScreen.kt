package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.sampledata.sampleItemsData
import com.example.rentals.ui.components.itemselection.CartSummary
import com.example.rentals.ui.components.itemselection.FilterSection
import com.example.rentals.ui.components.itemselection.ItemSelectionTopBar
import com.example.rentals.ui.components.itemselection.ItemList
import com.example.rentals.ui.viewmodels.CartViewModel

@Composable
fun ItemSelectionScreen(viewModel: CartViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { ItemSelectionTopBar() }, bottomBar = {
        CartSummary(10, onContinueClick = {}, totalPrice = 100.00)
    }) {innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Spacer(modifier = Modifier.height(10.dp))
            FilterSection()
            Spacer(modifier = Modifier.height(10.dp))
            ItemList(filteredItems = uiState)
        }
    }
}