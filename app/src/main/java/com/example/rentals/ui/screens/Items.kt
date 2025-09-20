package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.rentals.data.itemsFilters
import com.example.rentals.navigation.AddItem
import com.example.rentals.ui.components.shared.CustomSearchBar
import com.example.rentals.ui.components.shared.LoadingIndicator
import com.example.rentals.ui.components.items.StoreItemCard
import com.example.rentals.ui.components.shared.FilterSection
import com.example.rentals.ui.components.shared.ListHeader
import com.example.rentals.ui.viewmodels.ItemsViewModel

@Composable
fun ItemsScreen(itemsViewModel: ItemsViewModel = viewModel(), backStack: SnapshotStateList<Any>) {

    val itemsUiState by itemsViewModel.uiState.collectAsStateWithLifecycle()

    val textFieldState = rememberTextFieldState()

    LaunchedEffect(Unit) {
        itemsViewModel.getData()
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if(itemsUiState.isLoading) {
            LoadingIndicator(modifier = Modifier.weight(1f))
        }else {
            Scaffold(topBar = {
                CustomSearchBar(
                    label = "Search Items",
                    textFieldState = textFieldState,
                    onSearch = {},
                    searchResults = emptyList()
                )
            }, contentWindowInsets = WindowInsets(top = 10.dp),modifier = Modifier, floatingActionButton = {
                FloatingActionButton(onClick = {
                    backStack.add(AddItem)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "") }
            }) {innerPadding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    FilterSection(itemsFilters)
                    Spacer(modifier = Modifier.height(10.dp))
                    ListHeader("All Items (${itemsUiState.items.count()})", modifier = Modifier.padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(contentPadding = PaddingValues(bottom = 80.dp, start = 15.dp, end = 15.dp),  verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(itemsUiState.items) {it ->
                            StoreItemCard(it)
                        }
                    }
                    }
                }
            }
        }
    }
