package com.vizo.app.ui.create

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun CreateScreen(
    navController: NavController,
    viewModel: CreateViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            kotlinx.coroutines.delay(2000)
            viewModel.resetSaved()
        }
    }

    Scaffold(
        bottomBar = { VizoBottomBar(navController) },
        containerColor = VizoBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "✏️ Créer un statut",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            // Templates
            Text(
                text = "Choisis un template",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(vizoTemplates) { template ->
                    TemplateCard(
                        template = template,
                        isSelected = uiState.selectedTemplate == template.id,
                        onClick = { viewModel.selectTemplate(template.id) }
                    )
                }
            }

            // Éditeur
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VizoSurface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.title,
                        onValueChange = viewModel::updateTitle,
                        label = { Text("Titre du statut", color = VizoTextSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VizoPrimary,
                            unfocusedBorderColor = VizoTextMuted,
                            focusedTextColor = VizoTextPrimary,
                            unfocusedTextColor = VizoTextPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = uiState.content,
                        onValueChange = viewModel::updateContent,
                        label = {
                            Text(
                                vizoTemplates.getOrNull(uiState.selectedTemplate)?.placeholder
                                    ?: "Contenu de ton statut...",
                                color = VizoTextSecondary
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VizoPrimary,
                            unfocusedBorderColor = VizoTextMuted,
                            focusedTextColor = VizoTextPrimary,
                            unfocusedTextColor = VizoTextPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        maxLines = 8
                    )

                    // Compteur caractères
                    Text(
                        text = "${uiState.content.length} / 700 caractères",
                        fontSize = 11.sp,
                        color = if (uiState.content.length > 700) VizoError else VizoTextMuted,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }

            // Prévisualisation
            if (uiState.content.isNotBlank()) {
                Text(
                    text = "Prévisualisation",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoTextPrimary
                )
                StatusPreviewCard(
                    emoji = vizoTemplates.getOrNull(uiState.selectedTemplate)?.emoji ?: "📱",
                    title = uiState.title,
                    content = uiState.content
                )
            }

            // Messages
            if (uiState.isSaved) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = VizoPrimary.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        "Statut publié ! 🎉",
                        color = VizoPrimary,
                        modifier = Modifier.padding(12.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            uiState.error?.let {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = VizoError.copy(alpha = 0.1f)
                    )
                ) {
                    Text(it, color = VizoError, modifier = Modifier.padding(12.dp))
                }
            }

            // Boutons actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Brouillon
                OutlinedButton(
                    onClick = { viewModel.saveStatus(isDraft = true) },
                    modifier = Modifier.weight(1f).height(48.dp),
                    enabled = uiState.content.isNotBlank() && !uiState.isSaving,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = VizoPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Brouillon", color = VizoPrimary, fontWeight = FontWeight.Bold)
                }

                // Partager sur WhatsApp
                Button(
                    onClick = {
                        val text = buildString {
                            if (uiState.title.isNotBlank()) append("*${uiState.title}*\n\n")
                            append(uiState.content)
                        }
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, text)
                            setPackage("com.whatsapp")
                        }
                        try {
                            context.startActivity(intent)
                            viewModel.saveStatus(isDraft = false)
                        } catch (e: Exception) {
                            // WhatsApp non installé — partage générique
                            context.startActivity(
                                Intent.createChooser(
                                    Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, text)
                                    },
                                    "Partager le statut"
                                )
                            )
                        }
                    },
                    modifier = Modifier.weight(1f).height(48.dp),
                    enabled = uiState.content.isNotBlank() && !uiState.isSaving,
                    colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (uiState.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = VizoBackground
                        )
                    } else {
                        Text(
                            "📤 WhatsApp",
                            color = VizoBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TemplateCard(
    template: VizoTemplate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) VizoPrimary.copy(alpha = 0.15f) else VizoSurface)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) VizoPrimary else androidx.compose.ui.graphics.Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(template.emoji, fontSize = 24.sp)
            Text(
                text = template.category,
                fontSize = 10.sp,
                color = if (isSelected) VizoPrimary else VizoTextSecondary,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun StatusPreviewCard(emoji: String, title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VizoCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(emoji, fontSize = 20.sp)
                Text(
                    text = "Aperçu WhatsApp",
                    fontSize = 11.sp,
                    color = VizoTextSecondary
                )
            }
            if (title.isNotBlank()) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoTextPrimary
                )
            }
            Text(
                text = content,
                fontSize = 14.sp,
                color = VizoTextSecondary,
                lineHeight = 20.sp
            )
        }
    }
}
