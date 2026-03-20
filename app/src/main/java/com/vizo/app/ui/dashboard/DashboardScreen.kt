package com.vizo.app.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Bonjour 👋",
                        fontSize = 14.sp,
                        color = VizoTextSecondary
                    )
                    Text(
                        text = uiState.user?.name ?: "...",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoTextPrimary
                    )
                }
                // Badge fondateur
                if (uiState.user?.isFounder == true) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(VizoPrimary.copy(alpha = 0.2f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "⭐ Fondateur #${uiState.user?.founderRank}",
                            fontSize = 12.sp,
                            color = VizoPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Carte Essai / Abonnement
            if (!uiState.hasActiveSubscription && uiState.isTrialActive) {
                TrialCard(
                    hoursRemaining = uiState.trialHoursRemaining,
                    onSubscribe = { navController.navigate(Screen.Subscription.route) }
                )
            }

            // Carte Contacts
            ContactsCard(
                total = uiState.totalContacts,
                founders = uiState.founderContacts,
                regular = uiState.regularContacts,
                onViewAll = { navController.navigate(Screen.Contacts.route) }
            )

            // Carte Boost
            BoostCard(
                hasBoost = uiState.hasActiveBoost,
                hoursRemaining = uiState.boostHoursRemaining,
                onBuyBoost = { navController.navigate(Screen.Boost.route) }
            )

            // Actions rapides
            QuickActions(navController = navController)
        }
    }
}

@Composable
fun TrialCard(hoursRemaining: Long, onSubscribe: () -> Unit) {
    val color = when {
        hoursRemaining > 48 -> VizoPrimary
        hoursRemaining > 24 -> VizoWarning
        else -> VizoError
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VizoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("⏱ Essai gratuit", fontSize = 12.sp, color = VizoTextSecondary)
                Text(
                    text = "${hoursRemaining}h restantes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
            Button(
                onClick = onSubscribe,
                colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("S'abonner", color = VizoBackground, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ContactsCard(total: Int, founders: Int, regular: Int, onViewAll: () -> Unit) {
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
                Text("👥 Mon réseau", fontSize = 14.sp, color = VizoTextSecondary)
                TextButton(onClick = onViewAll) {
                    Text("Voir tout", color = VizoPrimary, fontSize = 12.sp)
                }
            }
            Text(
                text = "$total contacts",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatChip(label = "Fondateurs", value = "$founders/120", color = VizoPrimary)
                StatChip(label = "Réguliers", value = "$regular", color = VizoTextSecondary)
            }
        }
    }
}

@Composable
fun BoostCard(hasBoost: Boolean, hoursRemaining: Long, onBuyBoost: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (hasBoost) VizoBoostGold.copy(alpha = 0.1f) else VizoSurface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("⚡ Boost", fontSize = 14.sp, color = VizoTextSecondary)
                if (hasBoost) {
                    Text(
                        text = "+4 contacts/heure",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoBoostGold
                    )
                    Text("${hoursRemaining}h restantes", fontSize = 12.sp, color = VizoTextSecondary)
                } else {
                    Text(
                        text = "Accélère ta croissance",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoTextPrimary
                    )
                }
            }
            if (!hasBoost) {
                Button(
                    onClick = onBuyBoost,
                    colors = ButtonDefaults.buttonColors(containerColor = VizoBoostGold),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Booster", color = VizoBackground, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun QuickActions(navController: NavController) {
    Text("Actions rapides", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VizoTextPrimary)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionButton(
            modifier = Modifier.weight(1f),
            icon = "✏️",
            label = "Créer",
            onClick = { navController.navigate(Screen.Create.route) }
        )
        QuickActionButton(
            modifier = Modifier.weight(1f),
            icon = "📊",
            label = "Analytics",
            onClick = { navController.navigate(Screen.Analytics.route) }
        )
        QuickActionButton(
            modifier = Modifier.weight(1f),
            icon = "💰",
            label = "Revenus",
            onClick = { navController.navigate(Screen.Affiliate.route) }
        )
    }
}

@Composable
fun QuickActionButton(
    modifier: Modifier = Modifier,
    icon: String,
    label: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = VizoCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(icon, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, fontSize = 12.sp, color = VizoTextSecondary)
        }
    }
}

