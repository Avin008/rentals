package com.example.rentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.rentals.navigation.AppNavigation
import com.example.rentals.navigation.Destinations
import com.example.rentals.navigation.Home
import com.example.rentals.navigation.ItemSelection
import com.example.rentals.navigation.bottomNavItems
import com.example.rentals.ui.components.homescreen.TopNavigationBar
import com.example.rentals.ui.components.shared.BottomNavigationBar
import com.example.rentals.ui.theme.RentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentalsTheme {
                val backStack = remember { mutableStateListOf<Any>(Home) }
                val currentRoute = backStack.last()
                val showBottomNavbar = bottomNavItems.find { it -> it.route == currentRoute } !== null
                val showTopBar = backStack.last() == Destinations.Home.route
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if(showTopBar) TopNavigationBar()
                    },
                    bottomBar = {
                        if(showBottomNavbar) {
                            BottomNavigationBar(currentRoute = currentRoute,onNavigate = {route ->
                                backStack.add(route)
                            })
                        }
                    },
                    floatingActionButton = {
                        if (showTopBar){
                            FloatingActionButton(onClick = {
                                backStack.add(
                                    ItemSelection(
                                        deliveryDate = "",
                                        pickupDate = "",
                                        deliveryTime = "",
                                        pickupTime = ""
                                    )
                                )
                            }) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding), backStack = backStack)
                }
            }
        }
    }
}

