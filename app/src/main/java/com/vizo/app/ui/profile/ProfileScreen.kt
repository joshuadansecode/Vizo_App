package com.vizo.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.navigation.Screen
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var editName by remember { mutableStateOf("") }
    var editBio by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var showSignOutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            editName = it.name ?: ""
            editBio = it.bio ?: ""
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
                text = "Mon Profil",
                style = MaterialTheme.typography.displayLarge,
                color = VizoTextPrimary
            )

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = VizoPrimary)
                }
            } else {
                // Avatar + infos
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Avatar
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(VizoPrimary.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.user?.name?.firstOrNull()
                                    ?.uppercase() ?: "?",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = VizoPrimary
                            )
                        }

                        // Badge fondateur
                        if (uiState.user?.isFounder == true) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(VizoPrimary.copy(alpha = 0.15f))
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "⭐ Fondateur #${uiState.user?.founderRank}",
                                    color = VizoPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Text(
                            text = uiState.user?.phoneNumber ?: "",
                            color = VizoTextSecondary,
                            fontSize = 14.sp
                        )
                    }
                }

                // Formulaire édition
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Informations",
                                color = VizoTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            TextButton(onClick = { isEditing = !isEditing }) {
                                Text(
                                    if (isEditing) "Annuler" else "Modifier",
                                    color = VizoPrimary
                                )
                            }
                        }

                        OutlinedTextField(
                            value = editName,
                            onValueChange = { if (isEditing) editName = it },
                            label = { Text("Nom", color = VizoTextSecondary) },
                            readOnly = !isEditing,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = VizoPrimary,
                                unfocusedBorderColor = if (isEditing) VizoTextMuted else VizoSurface,
                                focusedTextColor = VizoTextPrimary,
                                unfocusedTextColor = VizoTextPrimary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        OutlinedTextField(
                            value = editBio,
                            onValueChange = { if (isEditing) editBio = it },
                            label = { Text("Bio", color = VizoTextSecondary) },
                            readOnly = !isEditing,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = VizoPrimary,
                                unfocusedBorderColor = if (isEditing) VizoTextMuted else VizoSurface,
                                focusedTextColor = VizoTextPrimary,
                                unfocusedTextColor = VizoTextPrimary
                            ),
                            shape = RoundedCornerShape(8.dp),
                            maxLines = 3
                        )

                        if (isEditing) {
                            Button(
                                onClick = {
                                    viewModel.updateProfile(editName, editBio)
                                    isEditing = false
                                },
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                enabled = editName.isNotBlank() && !uiState.isSaving,
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
                                        "Sauvegarder",
                                        color = VizoBackground,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // Messages
                uiState.successMessage?.let {
                    Card(colors = CardDefaults.cardColors(containerColor = VizoPrimary.copy(alpha = 0.1f))) {
                        Text(it, color = VizoPrimary, modifier = Modifier.padding(12.dp))
                    }
                }
                uiState.error?.let {
                    Card(colors = CardDefaults.cardColors(containerColor = VizoError.copy(alpha = 0.1f))) {
                        Text(it, color = VizoError, modifier = Modifier.padding(12.dp))
                    }
                }

                // Section abonnement
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "Abonnement",
                            color = VizoTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Button(
                            onClick = { navController.navigate(Screen.Subscription.route) },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                "Gérer mon abonnement",
                                color = VizoBackground,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Déconnexion
                OutlinedButton(
                    onClick = { showSignOutDialog = true },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = VizoError),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(VizoError)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Se déconnecter", color = VizoError, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            containerColor = VizoSurface,
            title = { Text("Se déconnecter ?", color = VizoTextPrimary, fontWeight = FontWeight.Bold) },
            text = { Text("Tu devras te reconnecter avec ton numéro de téléphone.", color = VizoTextSecondary) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.signOut {
                            navController.navigate(Screen.Phone.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VizoError)
                ) {
                    Text("Déconnecter", color = VizoTextPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSignOutDialog = false }) {
                    Text("Annuler", color = VizoTextSecondary)
                }
            }
        )
    }
}
