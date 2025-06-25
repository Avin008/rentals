package com.example.rentals.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentals.ui.theme.RentalsTheme
import java.time.LocalDate

@Composable
fun HorizontalDateScroller(
    dates: List<LocalDate>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val today = LocalDate.now()
    val listState = rememberLazyListState()

    LaunchedEffect(selectedDate, dates) {
        val selectedIndex = dates.indexOf(selectedDate)
        if (selectedIndex != -1) {
            listState.animateScrollToItem(index = selectedIndex)
        }
    }

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = dates, key = { it.toEpochDay() }) { date ->
            DateItem(
                date = date,
                isSelected = date == selectedDate,
                isToday = date == today,
                onClick = onDateSelected
            )
        }
    }
}

@Preview(showBackground = true, name = "Horizontal Date Scroller Preview")
@Composable
fun HorizontalDateScrollerPreview() {
    RentalsTheme {
        val datesForPreview = List(7) { index -> LocalDate.now().plusDays(index.toLong()) }
        HorizontalDateScroller(
            dates = datesForPreview,
            selectedDate = LocalDate.now(),
            onDateSelected = { }
        )
    }
}
