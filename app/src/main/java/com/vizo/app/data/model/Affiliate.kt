package com.vizo.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Referral(
    val id: String,
    @SerialName("referrer_id") val referrerId: String,
    @SerialName("referred_id") val referredId: String,
    val status: String,
    @SerialName("converted_at") val convertedAt: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class Commission(
    val id: String,
    @SerialName("affiliate_id") val affiliateId: String,
    @SerialName("transaction_id") val transactionId: String,
    val amount: Double,
    val status: String,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class Withdrawal(
    val id: String,
    @SerialName("user_id") val userId: String,
    val amount: Double,
    val method: String,
    val status: String,
    @SerialName("requested_at") val requestedAt: String? = null,
    @SerialName("processed_at") val processedAt: String? = null
)
