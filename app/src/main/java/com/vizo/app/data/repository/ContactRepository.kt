package com.vizo.app.data.repository

import com.vizo.app.data.model.Contact
import com.vizo.app.data.model.ContactStats
import com.vizo.app.data.remote.SupabaseProvider
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor() {

    suspend fun getContactStats(userId: String): ContactStats {
        val contacts = SupabaseProvider.client.postgrest
            .from("contacts")
            .select()
            .eq("user_id", userId)
            .decodeList<Contact>()

        val today = Instant.now().atOffset(ZoneOffset.UTC).toLocalDate()
        val todayAdded = contacts.count { contact ->
            val addedDate = Instant.parse(contact.addedAt)
                .atOffset(ZoneOffset.UTC).toLocalDate()
            addedDate == today
        }

        return ContactStats(
            total = contacts.size,
            founders = contacts.count { it.type == "FOUNDER" },
            regular = contacts.count { it.type == "REGULAR" },
            todayAdded = todayAdded
        )
    }

    suspend fun getContacts(userId: String): List<Contact> {
        return SupabaseProvider.client.postgrest
            .from("contacts")
            .select()
            .eq("user_id", userId)
            .decodeList<Contact>()
    }

    suspend fun canAddContact(userId: String, hasBoost: Boolean): Pair<Boolean, String?> {
        val stats = getContactStats(userId)

        // Limite fondateurs pas encore atteinte
        if (stats.founders < 120) return Pair(true, null)

        // Avec boost : 4 par heure
        if (hasBoost) {
            val lastHourCount = getContactsAddedLastHour(userId)
            return if (lastHourCount < 4) Pair(true, null)
            else Pair(false, "Limite boost atteinte (4/heure). Attends la prochaine heure.")
        }

        // Sans boost : 10 par jour
        return if (stats.todayAdded < 10) Pair(true, null)
        else Pair(false, "Limite quotidienne atteinte (10/jour). Reviens demain ou active un boost.")
    }

    private suspend fun getContactsAddedLastHour(userId: String): Int {
        val oneHourAgo = Instant.now().minusSeconds(3600)
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        val response = SupabaseProvider.client.postgrest
            .from("contacts")
            .select {
                count(io.github.jan.tennert.supabase.postgrest.query.Count.EXACT)
                filter {
                    eq("user_id", userId)
                    gte("added_at", oneHourAgo)
                }
            }
        return response.countOrNull()?.toInt() ?: 0
    }

    suspend fun addContact(
        userId: String,
        phoneNumber: String,
        name: String?,
        hasBoost: Boolean
    ): Result<Contact> {
        return try {
            val (canAdd, reason) = canAddContact(userId, hasBoost)
            if (!canAdd) return Result.failure(Exception(reason))

            // Vérifier doublon AVANT d'insérer
            val existing = SupabaseProvider.client.postgrest
                .from("contacts")
                .select()
                .eq("user_id", userId)
                .eq("phone_number", phoneNumber)
                .decodeSingleOrNull<Contact>()

            if (existing != null) {
                return Result.failure(Exception("Ce contact est déjà dans ton réseau."))
            }

            val stats = getContactStats(userId)
            val type = if (stats.founders < 120) "FOUNDER" else "REGULAR"

            val contact = mapOf(
                "user_id" to userId,
                "phone_number" to phoneNumber,
                "name" to name,
                "type" to type
            )

            SupabaseProvider.client.postgrest
                .from("contacts")
                .insert(contact)

            val added = SupabaseProvider.client.postgrest
                .from("contacts")
                .select()
                .eq("user_id", userId)
                .eq("phone_number", phoneNumber)
                .decodeSingle<Contact>()

            Result.success(added)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeContact(contactId: String): Result<Unit> {
        return try {
            SupabaseProvider.client.postgrest
                .from("contacts")
                .delete()
                .eq("id", contactId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
