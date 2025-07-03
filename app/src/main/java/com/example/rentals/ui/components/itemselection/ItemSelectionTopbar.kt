package com.example.rentals.ui.components.itemselection

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionTopBar() {
    TopAppBar(title = {
        Text(text = "Search")
    }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }, windowInsets = WindowInsets(top = 0))
}