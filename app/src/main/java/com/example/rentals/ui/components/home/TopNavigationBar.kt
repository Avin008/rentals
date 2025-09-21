package com.example.rentals.ui.components.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(navigateToProfile: () -> Any) {
    TopAppBar(title = { Text(text = "PiedPiper",  fontWeight = FontWeight.SemiBold) }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = ""
            )
        }
    }, actions = {
        IconButton(onClick = {
            navigateToProfile()
        }) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Account")
        }

    })
}