package com.vizo.app.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizo.app.data.remote.SupabaseProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateUiState(
    val title: String = "",
    val content: String = "",
    val selectedTemplate: Int = 0,
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)

val vizoTemplates = listOf(
    VizoTemplate(
        id = 0,
        emoji = "💼",
        category = "Business",
        title = "Offre spéciale",
        placeholder = "Décris ton offre ou service ici..."
    ),
    VizoTemplate(
        id = 1,
        emoji = "🔥",
        category = "Promo",
        title = "Promotion limitée",
        placeholder = "Quelle est ta promotion du jour ?"
    ),
    VizoTemplate(
        id = 2,
        emoji = "💡",
        category = "Motivation",
        title = "Conseil du jour",
        placeholder = "Partage une astuce ou un conseil utile..."
    ),
    VizoTemplate(
        id = 3,
        emoji = "🎉",
        category = "Événement",
        title = "Annonce événement",
        placeholder = "Décris ton événement, date, lieu..."
    ),
    VizoTemplate(
        id = 4,
        emoji = "📢",
        category = "Annonce",
        title = "Nouvelle importante",
        placeholder = "Quelle est ton annonce ?"
    ),
    VizoTemplate(
        id = 5,
        emoji = "⭐",
        category = "Témoignage",
        title = "Avis client",
        placeholder = "Partage un témoignage positif..."
    )
)

data class VizoTemplate(
    val id: Int,
    val emoji: String,
    val category: String,
    val title: String,
    val placeholder: String
)

@HiltViewModel
class CreateViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CreateUiState())
    val uiState: StateFlow<CreateUiState> = _uiState.asStateFlow()

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateContent(content: String) {
        _uiState.value = _uiState.value.copy(content = content)
    }

    fun selectTemplate(id: Int) {
        _uiState.value = _uiState.value.copy(
            selectedTemplate = id,
            title = vizoTemplates[id].title
        )
    }

    fun saveStatus(isDraft: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true)
            try {
                val userId = SupabaseProvider.client.auth
                    .currentUserOrNull()?.id ?: return@launch

                SupabaseProvider.client.postgrest
                    .from("statuses")
                    .insert(mapOf(
                        "user_id" to userId,
                        "title" to _uiState.value.title,
                        "content" to _uiState.value.content,
                        "is_draft" to isDraft,
                        "published_at" to if (!isDraft)
                            java.time.Instant.now().toString() else null
                    ))

                _uiState.value = _uiState.value.copy(
                    isSaving = false,
                    isSaved = true,
                    title = "",
                    content = ""
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSaving = false,
                    error = e.message
                )
            }
        }
    }

    fun resetSaved() {
        _uiState.value = _uiState.value.copy(isSaved = false, error = null)
    }
}
