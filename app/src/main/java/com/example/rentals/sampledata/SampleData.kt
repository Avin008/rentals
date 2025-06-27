package com.example.rentals.sampledata

import com.example.rentals.data.DeliveryItem
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

val sampleOrderItems = listOf(
    DeliveryItem("1", "Anil Nag", LocalDate.now().toString(), LocalTime.of(10, 30).toString(), 3450.00, "Scheduled", "user123", null, null, OffsetDateTime.now(ZoneOffset.UTC).toString(), "Badmal"),
    DeliveryItem("2", "Nithya Tandi", LocalDate.now().toString(), LocalTime.of(11, 0).toString(), 12000.00, "Scheduled", "user456", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusHours(2).toString(), "Gandapatrapali"),
    DeliveryItem("3", "Rajni Chhatria", LocalDate.now().plusDays(1).toString(), LocalTime.of(9, 0).toString(), 7525.00, "Scheduled", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Makri"),
)