package com.vizo.app.ui.boost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.Boost
import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BoostUiState(
    val activeBoost: Boost? = null,
    val isLoading: Boolean = false,
    val isPurchasing: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

@HiltViewModel
class BoostViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BoostUiState())
    val uiState: StateFlow<BoostUiState> = _uiState.asStateFlow()

    init { loadBoost() }

    fun loadBoost() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val userId = SupabaseProvider.client.auth
                .currentUserOrNull()?.id ?: return@launch
            val boost = subscriptionRepository.getActiveBoost(userId)
            _uiState.value = _uiState.value.copy(
                activeBoost = boost,
                isLoading = false
            )
        }
    }

    // Pour l'instant : simulation locale — sera remplacé par paiement Mobile Money
    fun purchaseBoost(duration: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isPurchasing = true)
            try {
                val userId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: return@launch

                val now = java.time.Instant.now()
                val endDate = now.plusSeconds(duration * 24L * 3600)

                SupabaseProvider.client.postgrest
                    .from("boosts")
                    .insert(mapOf(
                        "user_id" to userId,
                        "duration" to duration,
                        "start_date" to now.toString(),
                        "end_date" to endDate.toString(),
                        "status" to "ACTIVE"
                    ))

                _uiState.value = _uiState.value.copy(
                    isPurchasing = false,
                    successMessage = "Boost $duration jour(s) activé ! ⚡"
                )
                loadBoost()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isPurchasing = false,
                    error = e.message
                )
            }
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(error = null, successMessage = null)
    }
}
