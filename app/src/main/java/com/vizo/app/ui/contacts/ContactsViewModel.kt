package com.vizo.app.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.model.Contact
import com.vizo.app.data.model.ContactStats
import com.vizo.app.data.remote.SupabaseProvider
import com.vizo.app.data.repository.ContactRepository
import com.vizo.app.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContactsUiState(
    val contacts: List<Contact> = emptyList(),
    val stats: ContactStats = ContactStats(0, 0, 0, 0),
    val hasActiveBoost: Boolean = false,
    val searchQuery: String = "",
    val selectedFilter: String = "ALL", // ALL, FOUNDER, REGULAR
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState: StateFlow<ContactsUiState> = _uiState.asStateFlow()

    init { loadContacts() }

    fun loadContacts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: return@launch
                val contacts = contactRepository.getContacts(userId)
                val stats = contactRepository.getContactStats(userId)
                val boost = subscriptionRepository.getActiveBoost(userId)
                _uiState.value = _uiState.value.copy(
                    contacts = contacts,
                    stats = stats,
                    hasActiveBoost = boost != null,
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

    fun addContact(phoneNumber: String, name: String?) {
        viewModelScope.launch {
            val userId = SupabaseProvider.client.auth
                .currentUserOrNull()?.id ?: return@launch
            contactRepository.addContact(
                userId = userId,
                phoneNumber = phoneNumber,
                name = name,
                hasBoost = _uiState.value.hasActiveBoost
            ).onSuccess {
                _uiState.value = _uiState.value.copy(
                    successMessage = "Contact ajouté ✅"
                )
                loadContacts()
            }.onFailure {
                _uiState.value = _uiState.value.copy(error = it.message)
            }
        }
    }

    fun removeContact(contactId: String) {
        viewModelScope.launch {
            contactRepository.removeContact(contactId)
                .onSuccess { loadContacts() }
                .onFailure {
                    _uiState.value = _uiState.value.copy(error = it.message)
                }
        }
    }

    fun updateSearch(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun updateFilter(filter: String) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(error = null, successMessage = null)
    }

    val filteredContacts: List<Contact>
        get() {
            val state = _uiState.value
            return state.contacts
                .filter { contact ->
                    when (state.selectedFilter) {
                        "FOUNDER" -> contact.type == "FOUNDER"
                        "REGULAR" -> contact.type == "REGULAR"
                        else -> true
                    }
                }
                .filter { contact ->
                    if (state.searchQuery.isBlank()) true
                    else contact.name?.contains(state.searchQuery, ignoreCase = true) == true ||
                            contact.phoneNumber.contains(state.searchQuery)
                }
        }
}
