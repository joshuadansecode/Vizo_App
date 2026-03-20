package com.vizo.app.ui.affiliate

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.data.model.Commission
import com.vizo.app.data.model.Referral
import com.vizo.app.data.model.Withdrawal
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun AffiliateScreen(
    navController: NavController,
    viewModel: AffiliateViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showWithdrawDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        bottomBar = { VizoBottomBar(navController) },
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
                text = "💰 Mes Revenus",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            // Carte solde
            BalanceCard(
                balance = uiState.balance,
                referralCode = uiState.referralCode,
                weeklyWithdrawals = uiState.weeklyWithdrawals,
                onWithdraw = { showWithdrawDialog = true },
                onShare = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Rejoins Vizo avec mon code ${uiState.referralCode} et gagne de la visibilité sur WhatsApp ! 🚀 https://vizo.app"
                        )
                    }
                    context.startActivity(Intent.createChooser(intent, "Partager mon code"))
                }
            )

            // Tabs
            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = VizoSurface,
                contentColor = VizoPrimary
            ) {
                listOf("Aperçu", "Filleuls", "Retraits").forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.selectTab(index) },
                        text = {
                            Text(
                                title,
                                color = if (uiState.selectedTab == index) VizoPrimary
                                else VizoTextSecondary
                            )
                        }
                    )
                }
            }

            // Messages
            uiState.successMessage?.let {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = VizoPrimary.copy(alpha = 0.1f)
                    )
                ) {
                    Text(it, color = VizoPrimary, modifier = Modifier.padding(12.dp))
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

            // Contenu par tab
            when (uiState.selectedTab) {
                0 -> CommissionsTab(commissions = uiState.commissions)
                1 -> ReferralsTab(referrals = uiState.referrals)
                2 -> WithdrawalsTab(withdrawals = uiState.withdrawals)
            }
        }
    }

    if (showWithdrawDialog) {
        WithdrawDialog(
            balance = uiState.balance,
            isLoading = uiState.isWithdrawing,
            onDismiss = { showWithdrawDialog = false },
            onConfirm = { amount, method, phone ->
                viewModel.requestWithdrawal(amount, method, phone)
                showWithdrawDialog = false
            }
        )
    }
}

@Composable
fun BalanceCard(
    balance: Double,
    referralCode: String,
    weeklyWithdrawals: Int,
    onWithdraw: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VizoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Solde disponible", fontSize = 14.sp, color = VizoTextSecondary)
            Text(
                text = "${balance.toInt()} FCFA",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = if (balance >= 2000) VizoPrimary else VizoTextPrimary
            )

            // Code parrainage
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(VizoCard)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Mon code", fontSize = 12.sp, color = VizoTextSecondary)
                    Text(
                        text = referralCode,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoPrimary,
                        letterSpacing = 3.sp
                    )
                }
                Button(
                    onClick = onShare,
                    colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Partager", color = VizoBackground, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Limite retraits
            Text(
                text = "Retraits cette semaine : $weeklyWithdrawals / 3",
                fontSize = 12.sp,
                color = if (weeklyWithdrawals >= 3) VizoError else VizoTextSecondary
            )

            // Bouton retrait
            Button(
                onClick = onWithdraw,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                enabled = balance >= 2000 && weeklyWithdrawals < 3,
                colors = ButtonDefaults.buttonColors(
                    containerColor = VizoPrimary,
                    disabledContainerColor = VizoTextMuted
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (balance < 2000) "Minimum 2000F requis"
                    else if (weeklyWithdrawals >= 3) "Limite hebdo atteinte"
                    else "Retirer mes gains",
                    fontWeight = FontWeight.Bold,
                    color = VizoBackground
                )
            }
        }
    }
}

@Composable
fun CommissionsTab(commissions: List<Commission>) {
    if (commissions.isEmpty()) {
        EmptyState(
            icon = "💸",
            message = "Aucune commission pour l'instant.\nParraine des amis pour gagner 20% sur leurs abonnements !"
        )
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(commissions) { commission ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoCard),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Commission 20%",
                                color = VizoTextPrimary,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = commission.createdAt?.take(10) ?: "",
                                color = VizoTextSecondary,
                                fontSize = 12.sp
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "+${commission.amount.toInt()}F",
                                color = VizoPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CommissionStatusBadge(status = commission.status)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommissionStatusBadge(status: String) {
    val (color, label) = when (status) {
        "AVAILABLE" -> VizoPrimary to "Disponible"
        "WITHDRAWN" -> VizoTextSecondary to "Retiré"
        else -> VizoWarning to "En attente"
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(label, fontSize = 10.sp, color = color)
    }
}

@Composable
fun ReferralsTab(referrals: List<Referral>) {
    if (referrals.isEmpty()) {
        EmptyState(
            icon = "👥",
            message = "Aucun filleul pour l'instant.\nPartage ton code et commence à gagner !"
        )
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(referrals) { referral ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoCard),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Filleul", color = VizoTextPrimary, fontWeight = FontWeight.Medium)
                        val (color, label) = when (referral.status) {
                            "ACTIVE" -> VizoPrimary to "Abonné ✓"
                            "PENDING" -> VizoWarning to "En attente"
                            else -> VizoTextSecondary to "Inactif"
                        }
                        Text(label, color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun WithdrawalsTab(withdrawals: List<Withdrawal>) {
    if (withdrawals.isEmpty()) {
        EmptyState(icon = "🏦", message = "Aucun retrait effectué pour l'instant.")
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(withdrawals) { withdrawal ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VizoCard),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "${withdrawal.amount.toInt()}F",
                                color = VizoTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                withdrawal.requestedAt?.take(10) ?: "",
                                color = VizoTextSecondary,
                                fontSize = 12.sp
                            )
                        }
                        val (color, label) = when (withdrawal.status) {
                            "COMPLETED" -> VizoPrimary to "Effectué ✓"
                            "PROCESSING" -> VizoWarning to "En cours"
                            "FAILED" -> VizoError to "Échoué"
                            else -> VizoWarning to "En attente"
                        }
                        Text(label, color = color, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun WithdrawDialog(
    balance: Double,
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Double, String, String) -> Unit
) {
    var amount by remember { mutableStateOf(balance.toInt().toString()) }
    var method by remember { mutableStateOf("MOBILE_MONEY") }
    var phone by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = VizoSurface,
        title = {
            Text("Retirer mes gains", color = VizoTextPrimary, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Montant (min 2000F)", color = VizoTextSecondary) },
                    suffix = { Text("FCFA", color = VizoTextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = VizoPrimary,
                        unfocusedBorderColor = VizoTextMuted,
                        focusedTextColor = VizoTextPrimary,
                        unfocusedTextColor = VizoTextPrimary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                // Méthode
                Text("Méthode de retrait", color = VizoTextSecondary, fontSize = 12.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("MOBILE_MONEY" to "Mobile Money", "BANK_TRANSFER" to "Virement").forEach { (value, label) ->
                        FilterChip(
                            selected = method == value,
                            onClick = { method = value },
                            label = { Text(label, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = VizoPrimary,
                                selectedLabelColor = VizoBackground
                            )
                        )
                    }
                }

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Numéro Mobile Money", color = VizoTextSecondary) },
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
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amt = amount.toDoubleOrNull() ?: 0.0
                    onConfirm(amt, method, phone)
                },
                enabled = !isLoading &&
                        (amount.toDoubleOrNull() ?: 0.0) >= 2000 &&
                        phone.length >= 8,
                colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), color = VizoBackground)
                } else {
                    Text("Confirmer", color = VizoBackground, fontWeight = FontWeight.Bold)
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Annuler", color = VizoTextSecondary)
            }
        }
    )
}

@Composable
fun EmptyState(icon: String, message: String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(icon, fontSize = 48.sp)
            Text(
                text = message,
                color = VizoTextSecondary,
                fontSize = 14.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
