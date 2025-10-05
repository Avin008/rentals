package com.example.rentals.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rentals.ui.components.home.OrderTabs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.navigation.OrderDetails
import com.example.rentals.ui.components.home.HorizontalDateScroller
import com.example.rentals.ui.components.home.SectionList
import com.example.rentals.ui.components.shared.LoadingIndicator

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), backStack: SnapshotStateList<Any>) {

    val tabList = listOf("Delivery", "Pickup", "OnGoing")
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.selectedDate, uiState.selectedTabIndex) {
        val deliveriesData = homeViewModel.dummyApiCall(uiState.selectedDate, uiState.selectedTabIndex)
        homeViewModel.getData(uiState.selectedDate, uiState.selectedTabIndex, deliveriesData)
    }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalDateScroller(
            dates = uiState.datesForPreview,
            selectedDate = uiState.selectedDate,
            onDateSelected = { selectedDate ->
                homeViewModel.changeDate(selectedDate)
            },
        )

        Spacer(modifier = Modifier.height(10.dp))

        OrderTabs(tabList = tabList, selectedTabIndex = uiState.selectedTabIndex, onSelectedTab = {tabIndex ->
            homeViewModel.selectTab(tabIndex)
        })

        Column(modifier = Modifier.weight(1f)) {
            when(uiState.selectedTabIndex) {
                0 -> {
                    if (!uiState.isLoading) Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(isRefreshing = uiState.isRefreshing, onRefresh = { homeViewModel.onRefreshing() }, orders = uiState.deliveries, onClick = {
                            backStack.add(OrderDetails(orderId = "12345"))
                        })
                    }else {
                       LoadingIndicator(modifier = Modifier.weight(1f))
                    }
                }
                1 -> {
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(
                            isRefreshing = uiState.isRefreshing,
                            onRefresh = { homeViewModel.onRefreshing() },
                            orders = uiState.deliveries,
                            onClick = {})
                    }
                }
                2 -> {
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        SectionList(
                            isRefreshing = uiState.isRefreshing,
                            onRefresh = { homeViewModel.onRefreshing() },
                            orders = uiState.deliveries,
                            onClick = {})
                    }
                }
            }
        }
    }
}