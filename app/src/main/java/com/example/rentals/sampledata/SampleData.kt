package com.example.rentals.sampledata

import com.example.rentals.data.DeliveryItem
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

val sampleOrderItems = listOf(
    DeliveryItem("1", "Anil Nag", LocalDate.now().toString(), LocalTime.of(10, 30).toString(), 3450.00, "delivery", "user123", null, null, OffsetDateTime.now(ZoneOffset.UTC).toString(), "Badmal"),
    DeliveryItem("11", "Mukesh Muna", LocalDate.now().toString(), LocalTime.of(10, 30).toString(), 1990.00, "delivery", "user123", null, null, OffsetDateTime.now(ZoneOffset.UTC).toString(), "Badmal"),
    DeliveryItem("12", "Manesh Suna", LocalDate.now().toString(), LocalTime.of(10, 30).toString(), 7590.00, "delivery", "user123", null, null, OffsetDateTime.now(ZoneOffset.UTC).toString(), "Badmal"),
    DeliveryItem("2", "Nithya Tandi", LocalDate.now().toString(), LocalTime.of(11, 0).toString(), 12000.00, "pickup", "user456", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusHours(2).toString(), "Gandapatrapali"),
    DeliveryItem("3", "Rajni Chhatria", LocalDate.now().plusDays(1).toString(), LocalTime.of(22, 0).toString(), 7525.00, "ongoing", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Makri"),
    DeliveryItem("4", "Rajan Barik", LocalDate.now().plusDays(1).toString(), LocalTime.of(9, 0).toString(), 7525.00, "delivery", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Barmuda"),
    DeliveryItem("5", "Navin Jain", LocalDate.now().plusDays(1).toString(), LocalTime.of(9, 0).toString(), 7525.00, "pickup", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Dharapgarh"),
    DeliveryItem("6", "Anubhav Patra", LocalDate.now().plusDays(3).toString(), LocalTime.of(9, 0).toString(), 7525.00, "delivery", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Kurla"),
    DeliveryItem("7", "Ananta Chattria", LocalDate.now().plusDays(4).toString(), LocalTime.of(9, 0).toString(), 7525.00, "ongoing", "user789", null, null, OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).toString(), "Nuapali"),
    )