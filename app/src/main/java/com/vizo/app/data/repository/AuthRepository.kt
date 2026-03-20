package com.vizo.app.data.repository

import com.vizo.app.data.remote.SupabaseProvider
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.OtpType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    // Envoyer OTP au numéro de téléphone
    suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        return try {
            SupabaseProvider.client.auth.signInWith(io.github.jan.supabase.auth.providers.Phone) {
                phone = phoneNumber
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Vérifier OTP
    suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit> {
        return try {
            SupabaseProvider.client.auth.verifyOtp(
                type = OtpType.Phone.SMS,
                phone = phoneNumber,
                token = otp
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Vérifier si session active
    fun isLoggedIn(): Boolean {
        return try {
            SupabaseProvider.client.auth.currentSessionOrNull() != null
        } catch (e: Exception) {
            false
        }
    }

    // Déconnexion
    suspend fun signOut(): Result<Unit> {
        return try {
            SupabaseProvider.client.auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
