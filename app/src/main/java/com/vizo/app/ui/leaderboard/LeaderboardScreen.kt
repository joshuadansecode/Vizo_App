package com.vizo.app.ui.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.ui.components.VizoBottomBar
import com.vizo.app.ui.theme.*

@Composable
fun LeaderboardScreen(
    navController: NavController,
    viewModel: LeaderboardViewModel = hiltViewModel()
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
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "🏆 Classement",
                style = MaterialTheme.typography.displayLarge,
                color = VizoTextPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tabs
            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = Color.Transparent,
                contentColor = VizoPrimary,
                divider = {},
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                        color = VizoPrimary
                    )
                }
            ) {
                listOf("Points", "Filleuls", "Visibilité").forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.selectTab(index) },
                        text = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (uiState.selectedTab == index) VizoPrimary else VizoTextSecondary
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState.isLoading && uiState.entries.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = VizoPrimary)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    // Podium
                    if (uiState.entries.size >= 3) {
                        item {
                            LeaderboardPodium(
                                topEntries = uiState.entries.take(3),
                                currentUserId = uiState.currentUserId
                            )
                        }
                    }

                    // Liste du reste
                    val remainingEntries = if (uiState.entries.size > 3) {
                        uiState.entries.drop(3)
                    } else {
                        emptyList()
                    }

                    itemsIndexed(remainingEntries) { index, entry ->
                        LeaderboardItem(
                            entry = entry,
                            rank = index + 4,
                            isCurrentUser = entry.id == uiState.currentUserId
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardPodium(topEntries: List<LeaderboardEntry>, currentUserId: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        // 2ème
        if (topEntries.size >= 2) {
            PodiumUser(entry = topEntries[1], rank = 2, height = 140.dp, color = Color(0xFFC0C0C0))
        }
        
        // 1er
        if (topEntries.size >= 1) {
            PodiumUser(entry = topEntries[0], rank = 1, height = 180.dp, color = Color(0xFFFFD700))
        }

        // 3ème
        if (topEntries.size >= 3) {
            PodiumUser(entry = topEntries[2], rank = 3, height = 120.dp, color = Color(0xFFCD7F32))
        }
    }
}

@Composable
fun PodiumUser(entry: LeaderboardEntry, rank: Int, height: androidx.compose.ui.unit.Dp, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(if (rank == 1) 80.dp else 64.dp)
                    .clip(CircleShape)
                    .background(VizoSurface)
                    .background(
                        Brush.radialGradient(
                            listOf(color.copy(alpha = 0.4f), Color.Transparent)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (entry.name?.take(1) ?: "U").uppercase(),
                    fontSize = if (rank == 1) 32.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
            
            // Badge rang
            Box(
                modifier = Modifier
                    .offset(y = 10.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text("#$rank", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = VizoBackground)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = entry.name ?: "Utilisateur",
            style = MaterialTheme.typography.bodyMedium,
            color = VizoTextPrimary,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "${entry.contactCount} pts",
            fontSize = 12.sp,
            color = VizoPrimary
        )
    }
}

@Composable
fun LeaderboardItem(rank: Int, entry: LeaderboardEntry, isCurrentUser: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isCurrentUser -> VizoPremium.copy(alpha = 0.1f)
                else -> VizoCard
            }
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Rang
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(VizoSurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "#$rank",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = VizoTextPrimary
                )
            }

            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(VizoPrimary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = entry.name?.firstOrNull()?.uppercase() ?: "?",
                    color = VizoPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = if (isCurrentUser) "${entry.name ?: "Toi"} (moi)"
                        else entry.name ?: "Membre Vizo",
                        color = VizoTextPrimary,
                        fontWeight = if (isCurrentUser) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    )
                    if (entry.isFounder) {
                        Text("⭐", fontSize = 12.sp)
                    }
                }
            Text(
                text = "${entry.contactCount} pts",
                style = MaterialTheme.typography.titleLarge,
                color = VizoPrimary
            )
        }
    }
}
