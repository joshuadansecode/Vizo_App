package com.vizo.app.ui.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.Subscription
import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SubscriptionUiState(
    val activeSubscription: Subscription? = null,
    val isLoading: Boolean = false,
    val isPurchasing: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SubscriptionUiState())
    val uiState: StateFlow<SubscriptionUiState> = _uiState.asStateFlow()

    init { loadSubscription() }

    fun loadSubscription() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val userId = SupabaseProvider.client.auth
                .currentUserOrNull()?.id ?: return@launch
            val sub = subscriptionRepository.getActiveSubscription(userId)
            _uiState.value = _uiState.value.copy(
                activeSubscription = sub,
                isLoading = false
            )
        }
    }

    // Simulation — sera remplacé par vrai paiement Mobile Money Sprint 7
    fun subscribe(plan: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isPurchasing = true)
            try {
                val userId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: return@launch

                val now = java.time.Instant.now()
                val days = if (plan == "WEEKLY") 7L else 30L
                val endDate = now.plusSeconds(days * 24 * 3600)

                SupabaseProvider.client.postgrest
                    .from("subscriptions")
                    .insert(mapOf(
                        "user_id" to userId,
                        "plan" to plan,
                        "status" to "ACTIVE",
                        "start_date" to now.toString(),
                        "end_date" to endDate.toString(),
                        "auto_renew" to true
                    ))

                _uiState.value = _uiState.value.copy(
                    isPurchasing = false,
                    successMessage = "Abonnement $plan activé ! 🎉"
                )
                loadSubscription()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isPurchasing = false,
                    error = e.message
                )
            }
        }
    }

    fun cancelSubscription() {
        viewModelScope.launch {
            val userId = SupabaseProvider.client.auth
                .currentUserOrNull()?.id ?: return@launch
            try {
                SupabaseProvider.client.postgrest
                    .from("subscriptions")
                    .update(mapOf("status" to "CANCELLED", "auto_renew" to false))
                    .eq("user_id", userId)
                    .eq("status", "ACTIVE")
                _uiState.value = _uiState.value.copy(
                    successMessage = "Abonnement annulé. Accès maintenu jusqu'à la fin de la période."
                )
                loadSubscription()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
