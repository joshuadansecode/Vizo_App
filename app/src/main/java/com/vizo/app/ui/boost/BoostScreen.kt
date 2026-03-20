package com.vizo.app.ui.boost

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
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun BoostScreen(
    navController: NavController,
    viewModel: BoostViewModel = hiltViewModel()
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "⚡ Boosts",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )
            Text(
                text = "Accélère la croissance de ton réseau",
                fontSize = 14.sp,
                color = VizoTextSecondary
            )

            // Boost actif
            uiState.activeBoost?.let { boost ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = VizoBoostGold.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("⚡ Boost actif", fontSize = 14.sp, color = VizoBoostGold)
                        Text(
                            text = "+4 contacts/heure",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = VizoBoostGold
                        )
                        Text(
                            text = "Expire le ${boost.endDate.take(10)}",
                            fontSize = 12.sp,
                            color = VizoTextSecondary
                        )
                    }
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

            // Offres boost
            Text(
                text = "Choisir un boost",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            BoostOption(
                duration = 1,
                price = 150,
                contacts = 96,
                isLoading = uiState.isPurchasing,
                onPurchase = { viewModel.purchaseBoost(1) }
            )
            BoostOption(
                duration = 3,
                price = 400,
                contacts = 288,
                isPopular = true,
                isLoading = uiState.isPurchasing,
                onPurchase = { viewModel.purchaseBoost(3) }
            )
            BoostOption(
                duration = 7,
                price = 700,
                contacts = 672,
                isLoading = uiState.isPurchasing,
                onPurchase = { viewModel.purchaseBoost(7) }
            )
        }
    }
}

@Composable
fun BoostOption(
    duration: Int,
    price: Int,
    contacts: Int,
    isPopular: Boolean = false,
    isLoading: Boolean,
    onPurchase: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isPopular) VizoBoostGold.copy(alpha = 0.08f) else VizoSurface
        ),
        shape = RoundedCornerShape(16.dp),
        border = if (isPopular) CardDefaults.outlinedCardBorder() else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "$duration jour${if (duration > 1) "s" else ""}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoTextPrimary
                    )
                    if (isPopular) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(VizoBoostGold)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
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
                Text(
                    text = "+4 contacts/heure • jusqu'à $contacts contacts",
                    fontSize = 12.sp,
                    color = VizoTextSecondary
                )
            }
            Button(
                onClick = onPurchase,
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPopular) VizoBoostGold else VizoPrimary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = VizoBackground
                    )
                } else {
                    Text(
                        text = "${price}F",
                        color = VizoBackground,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
