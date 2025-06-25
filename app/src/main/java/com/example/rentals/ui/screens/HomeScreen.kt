package com.example.rentals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rentals.ui.components.OrderTabs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun HomeScreen() {
    val tabList = listOf("Delivery", "Pickup", "OnGoing")
    var selectedTabIndex by remember { mutableIntStateOf(value = 0) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        OrderTabs(tabList = tabList, selectedTabIndex = selectedTabIndex, onSelectedTab = {tabIndex ->
            selectedTabIndex = tabIndex
        })
        Column(modifier = Modifier.weight(1f)) {
            when(selectedTabIndex) {
                0 -> {
                    Text(text = "Delivery")
                }
                1 -> {
                    Text(text = "Pickup")
                }
                2 -> {
                    Text(text = "OnGoing")
                }
            }
        }
    }
}