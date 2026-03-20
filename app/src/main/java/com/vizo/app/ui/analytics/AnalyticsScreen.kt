package com.vizo.app.ui.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun AnalyticsScreen(navController: NavController) {
    Scaffold(
        bottomBar = { VizoBottomBar(navController) },
        containerColor = VizoBackground
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(32.dp)
            ) {
                Text("📊", fontSize = 64.sp)
                Text(
                    text = "Analytics",
                    style = MaterialTheme.typography.displayLarge,
                    color = VizoTextPrimary
                )
                Text(
                    text = "Les statistiques détaillées arrivent bientôt.\nPublie tes premiers statuts pour commencer à suivre tes performances.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = VizoTextSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
