package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import com.example.rentals.ui.components.itemselection.ItemSelectionTopBar

@Composable
fun ItemSelectionScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { ItemSelectionTopBar() }) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {}
    }
}