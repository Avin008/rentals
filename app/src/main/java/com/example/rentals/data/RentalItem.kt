package com.example.rentals.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentalItem(
    @SerialName("user_id")
    val userId: String,
    @SerialName("item_id")
    val id: String,
    @SerialName("image_url")
    val imageUrl: String,
    val name: String,
    val category: String,
    @SerialName("price_per_day")
    val price: Double,
    val description: String,
    @SerialName("is_available")
    val isAvailable: Boolean,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("in_stock")
    val inStock: Int,
    @SerialName("total_items")
    val totalItems: Int,
)
