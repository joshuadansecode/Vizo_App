package com.vizo.app.ui.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.remote.SupabaseProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class LeaderboardEntry(
    val id: String,
    val name: String? = null,
    @SerialName("is_founder") val isFounder: Boolean = false,
    @SerialName("founder_rank") val founderRank: Int? = null,
    @SerialName("contact_count") val contactCount: Int = 0
)

data class LeaderboardUiState(
    val entries: List<LeaderboardEntry> = emptyList(),
    val currentUserId: String = "",
    val isLoading: Boolean = false,
    val selectedTab: Int = 0 
)

@HiltViewModel
class LeaderboardViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init { loadLeaderboard() }

    fun selectTab(tabIndex: Int) {
        _uiState.value = _uiState.value.copy(selectedTab = tabIndex)
        loadLeaderboard()
    }

    fun loadLeaderboard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val currentId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: ""

                // Logique de tri selon l'onglet
                val orderColumn = when (_uiState.value.selectedTab) {
                    0 -> "contact_count" // Points / Contacts
                    1 -> "referral_count" // Parrainages (à ajouter en DB idéalement)
                    else -> "created_at" // Visibilité / Activité
                }

                val entries = SupabaseProvider.client.postgrest
                    .from("users")
                    .select()
                    .order(orderColumn, ascending = false)
                    .limit(50)
                    .decodeList<LeaderboardEntry>()

                _uiState.value = _uiState.value.copy(
                    entries = entries,
                    currentUserId = currentId,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}
