package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rentals.ui.components.OrderTabs
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.ui.components.HorizontalDateScroller
import com.example.rentals.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val tabList = listOf("Delivery", "Pickup", "OnGoing")
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalDateScroller(
            selectedDate = uiState.selectedDate,
            onDateSelected = { selectedDate ->
                viewModel.changeDate(selectedDate)
            },
            dates = uiState.datesForPreview
        )

        Spacer(modifier = Modifier.height(10.dp))

        OrderTabs(tabList = tabList, selectedTabIndex = uiState.selectedTabIndex, onSelectedTab = {tabIndex ->
            viewModel.selectTab(tabIndex)
        })
        Column(modifier = Modifier.weight(1f)) {
            when(uiState.selectedTabIndex) {
                0 -> {
                }
                1 -> {
                }
                2 -> {
                }
            }
        }
    }
}