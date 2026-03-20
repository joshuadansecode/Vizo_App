package com.vizo.app.data.repository

import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.model.User
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class UserRepository @Inject constructor() {

    // Créer profil après OTP vérifié
    suspend fun createProfile(name: String, referralCode: String? = null): Result<User> {
        return try {
            val authUser = SupabaseProvider.client.auth.currentUserOrNull()
                ?: return Result.failure(Exception("Non authentifié"))

            val userId = authUser.id
            val phone = authUser.phone ?: ""
            val newReferralCode = generateReferralCode()

            // Vérifier si l'utilisateur est fondateur (parmi les 120 premiers)
            val response = SupabaseProvider.client.postgrest
                .from("users")
                .select(columns = Columns.NONE) {
                    count = Count.EXACT
                }
            val userCount = response.countOrNull() ?: 0L

            val isFounder = userCount < 120
            val founderRank = if (isFounder) (userCount + 1).toInt() else null

            val userMap = mutableMapOf<String, Any>(
                "id" to userId,
                "phone_number" to phone,
                "name" to name,
                "referral_code" to newReferralCode,
                "is_founder" to isFounder,
                "trial_ends_at" to java.time.Instant.now()
                    .plusSeconds(72 * 60 * 60)
                    .atOffset(java.time.ZoneOffset.UTC)
                    .format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            )
            founderRank?.let { userMap["founder_rank"] = it }
            referralCode?.let {
                val referrerId = findUserByReferralCode(it)
                referrerId?.let { id -> userMap["referred_by"] = id }
            }

            SupabaseProvider.client.postgrest
                .from("users")
                .insert(userMap)

            // Ajouter au community pool
            addToCommunityPool(userId, phone, name)

            // Créer lien parrainage si code fourni
            referralCode?.let { createReferral(it, userId) }

            val createdUser = getProfile(userId)
            Result.success(createdUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(userId: String): User {
        return SupabaseProvider.client.postgrest
            .from("users")
            .select()
            .eq("id", userId)
            .decodeSingle<User>()
    }

    suspend fun profileExists(userId: String): Boolean {
        return try {
            getProfile(userId)
            true
        } catch (e: io.ktor.client.plugins.ResponseException) {
            if (e.response.status == io.ktor.http.HttpStatusCode.NotFound) {
                false
            } else {
                throw e
            }
        } catch (e: Exception) {
            // Pour les autres erreurs (réseau, etc.), on considère que le profil n'est pas accessible
            // mais on pourrait affiner ici.
            false
        }
    }

    private suspend fun addToCommunityPool(userId: String, phone: String, name: String) {
        SupabaseProvider.client.postgrest
            .from("community_pool")
            .insert(mapOf(
                "user_id" to userId,
                "phone_number" to phone,
                "name" to name,
                "is_distributable" to true
            ))
    }

    private suspend fun findUserByReferralCode(code: String): String? {
        return try {
            val result = SupabaseProvider.client.postgrest
                .from("users")
                .select(Columns.raw("id"))
                .eq("referral_code", code)
                .decodeSingleOrNull<Map<String, String>>()
            result?.get("id")
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun createReferral(referralCode: String, referredId: String) {
        val referrerId = findUserByReferralCode(referralCode) ?: return
        SupabaseProvider.client.postgrest
            .from("referrals")
            .insert(mapOf(
                "referrer_id" to referrerId,
                "referred_id" to referredId,
                "status" to "PENDING"
            ))
    }

    private fun generateReferralCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..8).map { chars[Random.nextInt(chars.length)] }.joinToString("")
    }
}
