package com.example.rentals.ui.components.item_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.components.shared.Dropdown

@Composable
fun FilterSection(
    selectedCategory: String = "All",
    onCategorySelected: (String) -> Unit = {},
    selectedSortBy: String = "Sort by",
    onSortBySelected: (String) -> Unit = {}
) {
    val categories = listOf("All", "Electronics", "Furniture", "Appliances", "Tools", "Sports")
    val sortByOptions = listOf("Name", "Price: Low to High", "Price: High to Low", "Newest")

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            item {
                Dropdown(
                    selectedValue = selectedCategory,
                    options = categories,
                    onOptionSelected = onCategorySelected,
                    icon = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Category dropdown"
                )
            }
            item {
                Dropdown(
                    selectedValue = selectedSortBy,
                    options = sortByOptions,
                    onOptionSelected = onSortBySelected,
                    icon = Icons.Filled.FilterList,
                    contentDescription = "Sort by dropdown"
                )
            }
        }
    }
}