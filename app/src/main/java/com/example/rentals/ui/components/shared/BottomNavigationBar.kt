package com.example.rentals.ui.components.shared

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.rentals.navigation.bottomNavItems


@Composable
fun BottomNavigationBar(currentRoute: Any, onNavigate: (route: Any) -> Unit) {

            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.route == currentRoute,
                        onClick = {
                            onNavigate(item.route)
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
}