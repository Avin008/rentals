package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentals.data.ordersFilters
import com.example.rentals.ui.components.home.SectionList
import com.example.rentals.ui.components.shared.FilterSection
import com.example.rentals.ui.components.shared.CustomSearchBar
import com.example.rentals.ui.components.shared.LoadingIndicator
import com.example.rentals.ui.viewmodels.SearchViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = viewModel()) {

    val searchUiState by searchViewModel.uiState.collectAsStateWithLifecycle()

    val textFieldState = rememberTextFieldState()

    LaunchedEffect(Unit) {
        searchViewModel.getData()
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if(searchUiState.isLoading) {
            LoadingIndicator(modifier = Modifier.weight(1f))
        }else {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                CustomSearchBar(label = "Search Orders",textFieldState = textFieldState, onSearch = {}, searchResults = emptyList())
                Spacer(modifier = Modifier.height(10.dp))
                FilterSection(ordersFilters)
                Spacer(modifier = Modifier.height(10.dp))
                SectionList(
                    isRefreshing = false,
                    onRefresh = { },
                    orders = searchUiState.items,
                    onClick = {})
            }
            }
        }
}
