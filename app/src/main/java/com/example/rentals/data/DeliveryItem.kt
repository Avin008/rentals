package com.example.rentals.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryItem(
    @SerialName("id")
    val id: String,

    @SerialName("customer_name")
    val customerName: String,

    @SerialName("delivery_date")
    val deliveryDate: String,

    @SerialName("delivery_time")
    val deliveryTime: String,

    @SerialName("price")
    val price: Double,

    @SerialName("status")
    val status: String,

    @SerialName("userid")
    val userId: String?,

    @SerialName("return_date")
    val returnDate: String?,

    @SerialName("pickup_time")
    val pickupTime: String?,

    @SerialName("created_at")
    val timestamp: String,

    val address: String
)

