package com.example.rentals.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

@Serializable
data object Home: NavKey

@Serializable
data object Items: NavKey

@Composable
fun AppNavigation(modifier: Modifier, backStack:  SnapshotStateList<Any>) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Home Screen")
                        Button(onClick = {backStack.add(Items)}) {
                            Text(text = "GOTO ITEMS")
                        }
                    }
                }
                is Items -> NavEntry(key) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Items Screen")
                            Button(onClick = {backStack.removeLastOrNull()}) {
                                Text(text = "GOTO HOME")
                            }
                        }
                }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}