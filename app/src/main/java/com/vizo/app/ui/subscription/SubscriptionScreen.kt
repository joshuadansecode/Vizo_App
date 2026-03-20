package com.vizo.app.ui.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.ui.theme.*

@Composable
fun SubscriptionScreen(
    navController: NavController,
    viewModel: SubscriptionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showCancelDialog by remember { mutableStateOf(false) }

    Scaffold(containerColor = VizoBackground) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("← Retour", color = VizoPrimary)
                }
            }

            Text(
                text = "Abonnement",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            // Abonnement actif
            uiState.activeSubscription?.let { sub ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = VizoPrimary.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("✅ Abonnement actif", fontSize = 14.sp, color = VizoPrimary)
                        Text(
                            text = if (sub.plan == "WEEKLY") "Hebdomadaire" else "Mensuel",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = VizoTextPrimary
                        )
                        Text(
                            text = "Expire le ${sub.endDate.take(10)}",
                            fontSize = 12.sp,
                            color = VizoTextSecondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = { showCancelDialog = true },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = VizoError),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Annuler l'abonnement", color = VizoError)
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

            if (uiState.activeSubscription == null) {
                Text(
                    text = "Choisis ton abonnement",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoTextPrimary
                )

                // Carte Hebdomadaire
                PlanCard(
                    title = "Hebdomadaire",
                    price = "1 000",
                    period = "/ semaine",
                    features = listOf(
                        "✅ Accès complet au pool de contacts",
                        "✅ Analytics de performance",
                        "✅ Revenus d'affiliation",
                        "✅ Création de statuts illimitée"
                    ),
                    isLoading = uiState.isPurchasing,
                    onSubscribe = { viewModel.subscribe("WEEKLY") }
                )

                // Carte Mensuelle
                PlanCard(
                    title = "Mensuel",
                    price = "3 500",
                    period = "/ mois",
                    isPopular = true,
                    features = listOf(
                        "✅ Tout du plan hebdomadaire",
                        "✅ Économie de 500F vs hebdo",
                        "✅ Priorité support client",
                        "✅ Accès aux nouvelles fonctionnalités"
                    ),
                    isLoading = uiState.isPurchasing,
                    onSubscribe = { viewModel.subscribe("MONTHLY") }
                )
            }
        }
    }

    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = { showCancelDialog = false },
            containerColor = VizoSurface,
            title = {
                Text(
                    "Annuler l'abonnement ?",
                    color = VizoTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    "Tu garderas accès jusqu'à la fin de ta période payée. Tes contacts et revenus seront conservés.",
                    color = VizoTextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.cancelSubscription()
                        showCancelDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VizoError)
                ) {
                    Text("Confirmer", color = VizoTextPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCancelDialog = false }) {
                    Text("Garder l'abonnement", color = VizoPrimary)
                }
            }
        )
    }
}

@Composable
fun PlanCard(
    title: String,
    price: String,
    period: String,
    features: List<String>,
    isPopular: Boolean = false,
    isLoading: Boolean,
    onSubscribe: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isPopular) VizoPrimary.copy(alpha = 0.08f) else VizoSurface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = VizoTextPrimary)
                if (isPopular) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(VizoPrimary)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "Populaire",
                            fontSize = 10.sp,
                            color = VizoBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "$price F",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoPrimary
                )
                Text(period, fontSize = 14.sp, color = VizoTextSecondary)
            }

            features.forEach { feature ->
                Text(feature, fontSize = 13.sp, color = VizoTextSecondary)
            }

            Button(
                onClick = onSubscribe,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = VizoBackground)
                } else {
                    Text(
                        "S'abonner maintenant",
                        color = VizoBackground,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
