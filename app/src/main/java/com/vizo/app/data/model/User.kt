package com.vizo.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    @SerialName("phone_number") val phoneNumber: String,
    val name: String? = null,
    @SerialName("profile_picture") val profilePicture: String? = null,
    val bio: String? = null,
    @SerialName("referral_code") val referralCode: String,
    @SerialName("referred_by") val referredBy: String? = null,
    @SerialName("is_founder") val isFounder: Boolean = false,
    @SerialName("founder_rank") val founderRank: Int? = null,
    @SerialName("trial_ends_at") val trialEndsAt: String,
    @SerialName("created_at") val createdAt: String? = null
)
