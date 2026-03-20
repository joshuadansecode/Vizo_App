package com.vizo.app.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vizo.app.navigation.Screen
import com.vizo.app.ui.theme.*

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val destination by viewModel.destination.collectAsState()

    // Animation logo
    val scale = remember { Animatable(0.5f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
    }

    // Navigation selon session
    LaunchedEffect(destination) {
        when (destination) {
            is SplashDestination.Auth -> {
                navController.navigate(Screen.Phone.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
            is SplashDestination.Onboarding -> {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
            is SplashDestination.Dashboard -> {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VizoBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "VIZO",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = VizoPrimary,
                modifier = Modifier.scale(scale.value)
            )
            Text(
                text = "Visibilité • Revenus • Réseau",
                fontSize = 14.sp,
                color = VizoTextSecondary
            )
        }
    }
}
