package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rentals.ui.components.homescreen.OrderTabs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.navigation.ItemSelection
import com.example.rentals.ui.components.homescreen.HorizontalDateScroller
import com.example.rentals.ui.components.homescreen.SectionList
import com.example.rentals.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), backStack: SnapshotStateList<Any>) {
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
                    if (!uiState.isLoading) Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(isRefreshing = uiState.isRefreshing, onRefresh = { viewModel.onRefreshing() }, orders = uiState.deliveries, onClick = {
                            backStack.add(ItemSelection(deliveryDate = "", deliveryTime = "", pickupDate = "", pickupTime = ""))
                        })
                    }else {
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                        }
                    }
                }
                1 -> {
                    if (!uiState.isLoading) Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(isRefreshing = uiState.isRefreshing, onRefresh = { viewModel.onRefreshing() }, orders = uiState.deliveries, onClick = {})
                    }else {
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                        }
                    }
                }
                2 -> {
                    if (!uiState.isLoading) Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(isRefreshing = uiState.isRefreshing, onRefresh = { viewModel.onRefreshing() }, orders = uiState.deliveries, onClick = {})
                    }else {
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}