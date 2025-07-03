package com.example.rentals.ui.components.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.theme.RentalsTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateItem(
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: (LocalDate) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val cardColors = when {
        isSelected -> CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        isToday -> CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
        else -> CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = { onClick(date) },
                role = Role.Button,
                onClickLabel = "Select date: ${date.format(DateTimeFormatter.ofPattern("MMMM d"))}"
            ),
        shape = RoundedCornerShape(12.dp),
        colors = cardColors,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 1.dp,
            pressedElevation = if (isSelected) 6.dp else 3.dp
        ),
        border = if (isToday && !isSelected) {
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        } else {
            null
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("EEE")),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                color = cardColors.contentColor
            )
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = cardColors.contentColor
            )
        }
    }
}

@Preview(showBackground = true, name = "Date Item Selected Today")
@Composable
fun DateItemPreviewSelected() {
    RentalsTheme {
        DateItem(
            date = LocalDate.now(),
            isSelected = true,
            isToday = true,
            onClick = { }
        )
    }
}

@Preview(showBackground = true, name = "Date Item Not Selected Today")
@Composable
fun DateItemPreviewNotSelectedToday() {
    RentalsTheme {
        DateItem(
            date = LocalDate.now(),
            isSelected = false,
            isToday = true,
            onClick = { }
        )
    }
}

@Preview(showBackground = true, name = "Date Item Not Selected Not Today")
@Composable
fun DateItemPreviewNotSelectedNotToday() {
    RentalsTheme {
        DateItem(
            date = LocalDate.now().plusDays(1),
            isSelected = false,
            isToday = false,
            onClick = { }
        )
    }
}