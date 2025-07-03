package com.example.rentals.ui.components.homescreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTabs(tabList: List<String>, selectedTabIndex: Int, onSelectedTab: (tabIndex: Int) -> Unit) {
    SecondaryTabRow(selectedTabIndex = selectedTabIndex) {
        tabList.forEachIndexed { index, tab ->
            Tab(selected = index == selectedTabIndex, onClick = {
                onSelectedTab(index)
            }, text = {
                Text(text = tab)
            })
        }
    }
}