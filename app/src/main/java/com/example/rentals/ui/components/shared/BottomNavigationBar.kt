package com.example.rentals.ui.components.shared

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.rentals.navigation.bottomNavItems


@Composable
fun BottomNavigationBar() {

            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.title == "Home",
                        onClick = {},
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