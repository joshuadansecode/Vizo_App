package com.vizo.app.data.repository

import com.vizo.app.data.model.Boost
import com.vizo.app.data.model.Subscription
import com.vizo.app.data.remote.SupabaseProvider
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionRepository @Inject constructor() {

    suspend fun getActiveSubscription(userId: String): Subscription? {
        return try {
            SupabaseProvider.client.postgrest
                .from("subscriptions")
                .select()
                .eq("user_id", userId)
                .eq("status", "ACTIVE")
                .decodeSingleOrNull<Subscription>()
        } catch (e: Exception) { null }
    }

    suspend fun getActiveBoost(userId: String): Boost? {
        return try {
            SupabaseProvider.client.postgrest
                .from("boosts")
                .select()
                .eq("user_id", userId)
                .eq("status", "ACTIVE")
                .decodeSingleOrNull<Boost>()
        } catch (e: Exception) { null }
    }
}
