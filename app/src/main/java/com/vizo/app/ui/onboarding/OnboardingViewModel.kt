package com.vizo.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.User
import com.vizo.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OnboardingState {
    object Idle : OnboardingState()
    object Loading : OnboardingState()
    data class Success(val user: User) : OnboardingState()
    data class Error(val message: String) : OnboardingState()
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<OnboardingState>(OnboardingState.Idle)
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _referralCode = MutableStateFlow("")
    val referralCode: StateFlow<String> = _referralCode.asStateFlow()

    fun updateName(name: String) { _name.value = name }
    fun updateReferralCode(code: String) { _referralCode.value = code }

    fun nextStep() {
        if (_currentStep.value < 3) _currentStep.value++
    }

    fun createProfile() {
        viewModelScope.launch {
            _state.value = OnboardingState.Loading
            userRepository.createProfile(
                name = _name.value,
                referralCode = _referralCode.value.ifBlank { null }
            ).onSuccess { user ->
                _state.value = OnboardingState.Success(user)
            }.onFailure {
                _state.value = OnboardingState.Error(it.message ?: "Erreur création profil")
            }
        }
    }
}
