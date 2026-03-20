package com.vizo.app.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.data.model.Contact
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.successMessage) {
        if (uiState.successMessage != null) {
            kotlinx.coroutines.delay(2000)
            viewModel.clearMessages()
        }
    }

    Scaffold(
        bottomBar = { VizoBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = VizoPrimary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter", tint = VizoBackground)
            }
        },
        containerColor = VizoBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Text(
                text = "Mon réseau",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            // Jauge fondateurs
            FounderProgressCard(
                founders = uiState.stats.founders,
                hasBoost = uiState.hasActiveBoost,
                todayAdded = uiState.stats.todayAdded
            )

            // Barre de recherche
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::updateSearch,
                placeholder = { Text("Rechercher...", color = VizoTextMuted) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = VizoTextMuted)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VizoPrimary,
                    unfocusedBorderColor = VizoTextMuted,
                    focusedTextColor = VizoTextPrimary,
                    unfocusedTextColor = VizoTextPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            // Filtres
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("ALL" to "Tous", "FOUNDER" to "Fondateurs", "REGULAR" to "Réguliers")
                    .forEach { (value, label) ->
                        FilterChip(
                            selected = uiState.selectedFilter == value,
                            onClick = { viewModel.updateFilter(value) },
                            label = { Text(label, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = VizoPrimary,
                                selectedLabelColor = VizoBackground
                            )
                        )
                    }
            }

            // Messages
            uiState.successMessage?.let {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = VizoPrimary.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = it,
                        color = VizoPrimary,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 14.sp
                    )
                }
            }

            uiState.error?.let {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = VizoError.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = it,
                        color = VizoError,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 14.sp
                    )
                }
            }

            // Liste contacts
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = VizoPrimary)
                }
            } else {
                val filtered = viewModel.filteredContacts
                if (filtered.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Aucun contact pour l'instant.\nAppuie sur + pour en ajouter.",
                            color = VizoTextSecondary,
                            fontSize = 14.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(filtered, key = { it.id }) { contact ->
                            ContactItem(
                                contact = contact,
                                onDelete = { viewModel.removeContact(contact.id) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddContactDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { phone, name ->
                viewModel.addContact(phone, name)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun FounderProgressCard(founders: Int, hasBoost: Boolean, todayAdded: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VizoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("⭐ Contacts Fondateurs", fontSize = 14.sp, color = VizoTextSecondary)
                Text(
                    text = "$founders / 120",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoPrimary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { founders / 120f },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                color = VizoPrimary,
                trackColor = VizoCard
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (hasBoost) {
                    Text("⚡ +4 contacts/heure actif", fontSize = 12.sp, color = VizoBoostGold)
                } else {
                    Text("Limite: 10 contacts/jour", fontSize = 12.sp, color = VizoTextSecondary)
                }
                Text("Ajoutés aujourd'hui: $todayAdded", fontSize = 12.sp, color = VizoTextSecondary)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VizoCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(VizoPrimary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.name?.firstOrNull()?.uppercase() ?: "?",
                    color = VizoPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.name ?: contact.phoneNumber,
                    color = VizoTextPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                Text(
                    text = contact.phoneNumber,
                    color = VizoTextSecondary,
                    fontSize = 12.sp
                )
            }

            // Badge type
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (contact.type == "FOUNDER") VizoPrimary.copy(alpha = 0.15f)
                        else VizoTextMuted.copy(alpha = 0.15f)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (contact.type == "FOUNDER") "⭐" else "👤",
                    fontSize = 12.sp
                )
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Supprimer", tint = VizoError)
            }
        }
    }
}

@Composable
fun AddContactDialog(onDismiss: () -> Unit, onConfirm: (String, String?) -> Unit) {
    var phone by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = VizoSurface,
        title = {
            Text("Ajouter un contact", color = VizoTextPrimary, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Numéro (+229...)", color = VizoTextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nom (optionnel)", color = VizoTextSecondary) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VizoPrimary,
                        unfocusedBorderColor = VizoTextMuted,
                        focusedTextColor = VizoTextPrimary,
                        unfocusedTextColor = VizoTextPrimary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(phone, name.ifBlank { null }) },
                enabled = phone.length >= 8,
                colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary)
            ) {
                Text("Ajouter", color = VizoBackground, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Annuler", color = VizoTextSecondary)
            }
        }
    )
}
