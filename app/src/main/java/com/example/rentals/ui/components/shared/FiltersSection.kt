package com.example.rentals.ui.components.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.data.model.OrdersFilters

@Composable
fun FilterSection(filters: List<OrdersFilters>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 20.dp)) {
            items(filters, key = {it.id}) {it ->
                AssistChip(
                    onClick = {},
                    label = { Text(it.label, style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal)
                    },
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    ),
                    leadingIcon = {
                        Icon(
                            it.icon,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }
        }
    }
}