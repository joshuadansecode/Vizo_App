package com.vizo.app.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SplashDestination {
    object Loading : SplashDestination()
    object Auth : SplashDestination()
    object Onboarding : SplashDestination()
    object Dashboard : SplashDestination()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _destination = MutableStateFlow<SplashDestination>(SplashDestination.Loading)
    val destination: StateFlow<SplashDestination> = _destination.asStateFlow()

    init { checkSession() }

    private fun checkSession() {
        viewModelScope.launch {
            try {
                val session = SupabaseProvider.client.auth.currentSessionOrNull()

                if (session == null) {
                    _destination.value = SplashDestination.Auth
                    return@launch
                }

                val userId = session.user?.id ?: run {
                    _destination.value = SplashDestination.Auth
                    return@launch
                }

                val profileExists = userRepository.profileExists(userId)
                _destination.value = if (profileExists) {
                    SplashDestination.Dashboard
                } else {
                    SplashDestination.Onboarding
                }
            } catch (e: Exception) {
                _destination.value = SplashDestination.Auth
            }
        }
    }
}
