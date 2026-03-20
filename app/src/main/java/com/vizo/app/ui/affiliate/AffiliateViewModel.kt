package com.vizo.app.ui.affiliate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.Commission
import com.vizo.app.data.model.Referral
import com.vizo.app.data.model.Withdrawal
import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.repository.AffiliateRepository
import com.vizo.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AffiliateUiState(
    val referralCode: String = "",
    val balance: Double = 0.0,
    val referrals: List<Referral> = emptyList(),
    val commissions: List<Commission> = emptyList(),
    val withdrawals: List<Withdrawal> = emptyList(),
    val weeklyWithdrawals: Int = 0,
    val isLoading: Boolean = false,
    val isWithdrawing: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val selectedTab: Int = 0 // 0=Aperçu, 1=Filleuls, 2=Retraits
)

@HiltViewModel
class AffiliateViewModel @Inject constructor(
    private val affiliateRepository: AffiliateRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AffiliateUiState())
    val uiState: StateFlow<AffiliateUiState> = _uiState.asStateFlow()

    init { loadAffiliateData() }

    fun loadAffiliateData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: return@launch

                val referralCode = affiliateRepository.getReferralCode(userId)
                val balance = affiliateRepository.getBalance(userId).getOrDefault(0.0)
                val referrals = affiliateRepository.getReferrals(userId)
                val commissions = affiliateRepository.getCommissions(userId)
                val withdrawals = affiliateRepository.getWithdrawals(userId)
                val weeklyCount = affiliateRepository.getWeeklyWithdrawalCount(userId)

                _uiState.value = _uiState.value.copy(
                    referralCode = referralCode,
                    balance = balance,
                    referrals = referrals,
                    commissions = commissions,
                    withdrawals = withdrawals,
                    weeklyWithdrawals = weeklyCount,
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

    fun requestWithdrawal(amount: Double, method: String, phoneNumber: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isWithdrawing = true)
            val userId = SupabaseProvider.client.auth
                .currentUserOrNull()?.id ?: return@launch

            affiliateRepository.requestWithdrawal(userId, amount, method, phoneNumber)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isWithdrawing = false,
                        successMessage = "Retrait de ${amount.toInt()}F demandé ✅ Traitement sous 72h."
                    )
                    loadAffiliateData()
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isWithdrawing = false,
                        error = it.message
                    )
                }
        }
    }

    fun selectTab(tab: Int) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(error = null, successMessage = null)
    }
}
