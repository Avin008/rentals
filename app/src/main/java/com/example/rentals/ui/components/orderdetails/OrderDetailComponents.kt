package com.example.rentals.ui.components.orderdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun SummaryRow(label: String, value: Double, isTotal: Boolean = false) {
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    }
    val textStyle = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge
    val fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = textStyle, fontWeight = fontWeight)
        Text(text = currencyFormatter.format(value), style = textStyle, fontWeight = fontWeight)
    }
}
