package com.example.rentals.ui.components.itemselection

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionTopBar() {
    Surface(modifier = Modifier.fillMaxWidth(), shadowElevation = 4.dp) {
        TopAppBar(title = {
            Text(text = "Search")
        }, navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }, windowInsets = WindowInsets(top = 0))
    }
}