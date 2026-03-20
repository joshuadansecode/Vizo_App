package com.vizo.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.User
import com.vizo.app.data.repository.UserRepository
import com.vizo.app.data.repository.ContactRepository
import com.vizo.app.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

data class DashboardUiState(
    val user: User? = null,
    val totalContacts: Int = 0,
    val founderContacts: Int = 0,
    val regularContacts: Int = 0,
    val trialHoursRemaining: Long = 0,
    val isTrialActive: Boolean = true,
    val hasActiveSubscription: Boolean = false,
    val hasActiveBoost: Boolean = false,
    val boostHoursRemaining: Long = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val contactRepository: ContactRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userId = com.vizo.app.data.remote.SupabaseProvider
                    .client.auth.currentUserOrNull()?.id ?: return@launch

                val user = userRepository.getProfile(userId)
                val contactStats = contactRepository.getContactStats(userId)
                val subscription = subscriptionRepository.getActiveSubscription(userId)
                val boost = subscriptionRepository.getActiveBoost(userId)

                val trialEndsAt = Instant.parse(user.trialEndsAt)
                val now = Instant.now()
                val hoursRemaining = java.time.Duration.between(now, trialEndsAt).toHours()

                _uiState.value = DashboardUiState(
                    user = user,
                    totalContacts = contactStats.total,
                    founderContacts = contactStats.founders,
                    regularContacts = contactStats.regular,
                    trialHoursRemaining = maxOf(0, hoursRemaining),
                    isTrialActive = trialEndsAt.isAfter(now),
                    hasActiveSubscription = subscription != null,
                    hasActiveBoost = boost != null,
                    boostHoursRemaining = boost?.let {
                        java.time.Duration.between(now, Instant.parse(it.endDate)).toHours()
                    } ?: 0,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
