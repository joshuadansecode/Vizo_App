package com.vizo.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("phone_number") val phoneNumber: String,
    val name: String? = null,
    val type: String, // "FOUNDER" ou "REGULAR"
    @SerialName("added_at") val addedAt: String
)

data class ContactStats(
    val total: Int,
    val founders: Int,
    val regular: Int,
    val todayAdded: Int
)
