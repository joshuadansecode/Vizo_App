package com.vizo.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val id: String,
    @SerialName("user_id") val userId: String,
    val plan: String,
    val status: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("auto_renew") val autoRenew: Boolean = true
)

@Serializable
data class Boost(
    val id: String,
    @SerialName("user_id") val userId: String,
    val duration: Int,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    val status: String
)
