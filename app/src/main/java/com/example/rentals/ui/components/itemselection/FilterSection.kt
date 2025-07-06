package com.example.rentals.ui.components.itemselection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 20.dp)) {
            item {
                AssistChip(
                    onClick = {},
                    label = { Text("Category", style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal)
                            },
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }
            item {
                AssistChip(
                    onClick = {},
                    label = { Text("Sort By", style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal)
                    },
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.FilterList,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }
        }
    }
}