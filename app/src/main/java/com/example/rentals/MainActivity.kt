package com.example.rentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rentals.navigation.AppNavigation
import com.example.rentals.navigation.Home
import com.example.rentals.ui.components.TopNavigationBar
import com.example.rentals.ui.theme.RentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentalsTheme {
                val backStack = remember { mutableStateListOf<Any>(Home) }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopNavigationBar()
                    },
                ) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding), backStack = backStack)
                }
            }
        }
    }
}

