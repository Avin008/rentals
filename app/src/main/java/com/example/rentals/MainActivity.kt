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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.rentals.navigation.AppNavigation
import com.example.rentals.navigation.Destinations
import com.example.rentals.navigation.Home
import com.example.rentals.navigation.ItemSelection
import com.example.rentals.navigation.Profile
import com.example.rentals.navigation.bottomNavItems
import com.example.rentals.ui.components.home.CreateOrderBottomSheet
import com.example.rentals.ui.components.home.TopNavigationBar
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
                var showBottomSheet by rememberSaveable { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if(showTopBar) TopNavigationBar(navigateToProfile = {
                            backStack.add(Profile)
                        })
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
                               showBottomSheet = true
                            }) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding), backStack = backStack)
                    CreateOrderBottomSheet(showBottomSheet = showBottomSheet, onDismiss = {
                        showBottomSheet = false
                    }, onSuccess = {startDate, endDate, deliveryTime, returnTime ->
                      backStack.add(ItemSelection(startDate, deliveryTime, endDate, returnTime))
                        showBottomSheet = false
                    })
                }
            }
        }
    }
}

