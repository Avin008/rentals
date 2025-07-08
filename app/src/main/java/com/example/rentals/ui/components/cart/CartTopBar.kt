package com.example.rentals.ui.components.cart

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar() {
    CenterAlignedTopAppBar(windowInsets = WindowInsets(top = 0), title = { Text(text = "Cart", style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold) }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "")
        }
    })
}