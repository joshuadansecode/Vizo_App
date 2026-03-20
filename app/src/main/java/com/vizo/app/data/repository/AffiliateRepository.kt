package com.vizo.app.data.repository

import com.vizo.app.data.model.Commission
import com.vizo.app.data.model.Referral
import com.vizo.app.data.model.Withdrawal
import com.vizo.app.data.remote.SupabaseProvider
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AffiliateRepository @Inject constructor() {

    // Solde disponible
    suspend fun getBalance(userId: String): Result<Double> {
        return try {
            val commissions = SupabaseProvider.client.postgrest
                .from("commissions")
                .select()
                .eq("affiliate_id", userId)
                .eq("status", "AVAILABLE")
                .decodeList<Commission>()
            Result.success(commissions.sumOf { it.amount })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Liste des filleuls
    suspend fun getReferrals(userId: String): List<Referral> {
        return try {
            SupabaseProvider.client.postgrest
                .from("referrals")
                .select()
                .eq("referrer_id", userId)
                .decodeList<Referral>()
        } catch (e: Exception) { emptyList() }
    }

    // Historique commissions
    suspend fun getCommissions(userId: String): List<Commission> {
        return try {
            SupabaseProvider.client.postgrest
                .from("commissions")
                .select()
                .eq("affiliate_id", userId)
                .decodeList<Commission>()
        } catch (e: Exception) { emptyList() }
    }

    // Historique retraits
    suspend fun getWithdrawals(userId: String): List<Withdrawal> {
        return try {
            SupabaseProvider.client.postgrest
                .from("withdrawals")
                .select()
                .eq("user_id", userId)
                .decodeList<Withdrawal>()
        } catch (e: Exception) { emptyList() }
    }

    // Nombre de retraits cette semaine
    suspend fun getWeeklyWithdrawalCount(userId: String): Int {
        return try {
            val oneWeekAgo = java.time.Instant.now()
                .minusSeconds(7 * 24 * 3600)
                .toString()
            val response = SupabaseProvider.client.postgrest
                .from("withdrawals")
                .select(columns = Columns.NONE) {
                    count = Count.EXACT
                    filter {
                        eq("user_id", userId)
                        eq("status", "COMPLETED")
                        gte("requested_at", oneWeekAgo)
                    }
                }
            response.countOrNull()?.toInt() ?: 0
        } catch (e: Exception) { 0 }
    }

    // Demande de retrait
    suspend fun requestWithdrawal(
        userId: String,
        amount: Double,
        method: String,
        phoneNumber: String
    ): Result<Withdrawal> {
        return try {
            // Vérifications côté client
            val weeklyCount = getWeeklyWithdrawalCount(userId)
            if (weeklyCount >= 3) {
                return Result.failure(Exception("Limite de 3 retraits par semaine atteinte."))
            }

            // Appel RPC atomique
            val withdrawalId = SupabaseProvider.client.postgrest
                .rpc("process_withdrawal", mapOf(
                    "p_user_id" to userId,
                    "p_amount" to amount,
                    "p_method" to method,
                    "p_phone" to phoneNumber
                ))
                .decodeSingle<String>()

            val withdrawal = SupabaseProvider.client.postgrest
                .from("withdrawals")
                .select()
                .eq("id", withdrawalId)
                .decodeSingle<Withdrawal>()

            Result.success(withdrawal)
        } catch (e: Exception) {
            Result.failure(Exception(e.message?.substringAfter("ERROR: ") ?: "Erreur retrait"))
        }
    }

    // Code de parrainage de l'utilisateur
    suspend fun getReferralCode(userId: String): String {
        return try {
            val result = SupabaseProvider.client.postgrest
                .from("users")
                .select(Columns.raw("referral_code"))
                .eq("id", userId)
                .decodeSingle<Map<String, String>>()
            result["referral_code"] ?: ""
        } catch (e: Exception) { "" }
    }
}
