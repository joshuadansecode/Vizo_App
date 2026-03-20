package com.vizo.app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object OtpSent : AuthState()
    object Verified : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    fun updatePhone(phone: String) {
        _phoneNumber.value = phone
    }

    fun sendOtp() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val phone = formatPhone(_phoneNumber.value)
            authRepository.sendOtp(phone)
                .onSuccess { _authState.value = AuthState.OtpSent }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Erreur inconnue") }
        }
    }

    fun verifyOtp(otp: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val phone = formatPhone(_phoneNumber.value)
            authRepository.verifyOtp(phone, otp)
                .onSuccess { _authState.value = AuthState.Verified }
                .onFailure { _authState.value = AuthState.Error(it.message ?: "Code incorrect") }
        }
    }

    // Formate le numéro béninois → +229XXXXXXXX
    private fun formatPhone(phone: String): String {
        val digits = phone.filter { it.isDigit() }
        return if (digits.startsWith("229")) "+$digits"
        else "+229$digits"
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}
