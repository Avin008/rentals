package com.example.rentals.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun convert24To12HoursTime(time24Hour: String?): String? {
    if (time24Hour.isNullOrBlank()) {
        return null
    }

    return try {
        val inputParser = DateTimeFormatter.ofPattern("[H:mm][HH:mm]")
        val localTime = LocalTime.parse(time24Hour, inputParser)

        val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        localTime.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        null
    }
}