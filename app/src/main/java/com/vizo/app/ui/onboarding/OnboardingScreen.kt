package com.vizo.app.ui.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.vizo.app.ui.theme.*

@Composable
fun OnboardingScreen(
    onComplete: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentStep by viewModel.currentStep.collectAsState()
    val name by viewModel.name.collectAsState()
    val referralCode by viewModel.referralCode.collectAsState()

    LaunchedEffect(state) {
        if (state is OnboardingState.Success) onComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VizoBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(48.dp))

            // Indicateur de progression
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                (0..3).forEach { step ->
                    Box(
                        modifier = Modifier
                            .size(if (step == currentStep) 24.dp else 8.dp, 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (step <= currentStep) VizoPrimary
                                else VizoTextMuted
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Contenu par étape
            AnimatedContent(targetState = currentStep) { step ->
                when (step) {
                    0 -> StepWelcome()
                    1 -> StepName(name = name, onNameChange = viewModel::updateName)
                    2 -> StepReferral(code = referralCode, onCodeChange = viewModel::updateReferralCode)
                    3 -> StepReady()
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Erreur
            if (state is OnboardingState.Error) {
                Text(
                    text = (state as OnboardingState.Error).message,
                    color = VizoError,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Bouton
            Button(
                onClick = {
                    if (currentStep < 3) viewModel.nextStep()
                    else viewModel.createProfile()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = (currentStep != 1 || name.isNotBlank()) &&
                        state !is OnboardingState.Loading,
                colors = ButtonDefaults.buttonColors(containerColor = VizoPrimary),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (state is OnboardingState.Loading) {
                    CircularProgressIndicator(color = VizoBackground, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        text = if (currentStep < 3) "Suivant" else "Commencer Vizo \uD83D\uDE80",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun StepWelcome() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("👋", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Bienvenue sur Vizo",
            style = MaterialTheme.typography.displayLarge,
            color = VizoTextPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "La plateforme qui transforme ton WhatsApp en machine à visibilité et à revenus.",
            style = MaterialTheme.typography.bodyMedium,
            color = VizoTextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StepName(name: String, onNameChange: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("😎", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Comment tu t'appelles ?",
            style = MaterialTheme.typography.displayLarge,
            color = VizoTextPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Ton prénom ou pseudo", color = VizoTextSecondary) },
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
    }
}

@Composable
fun StepReferral(code: String, onCodeChange: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("\uD83C\uDF81", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tu as un code de parrainage ?",
            style = MaterialTheme.typography.displayLarge,
            color = VizoTextPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Facultatif — tu peux passer cette étape",
            style = MaterialTheme.typography.bodyMedium,
            color = VizoTextSecondary
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = code,
            onValueChange = { if (it.length <= 8) onCodeChange(it.uppercase()) },
            label = { Text("Code de parrainage", color = VizoTextSecondary) },
            placeholder = { Text("Ex: AB3X9K2M", color = VizoTextMuted) },
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
    }
}

@Composable
fun StepReady() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("\uD83D\uDE80", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tu es prêt !",
            style = MaterialTheme.typography.displayLarge,
            color = VizoTextPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Ton essai gratuit de 72h commence maintenant. Construis ton réseau et découvre la puissance de Vizo.",
            style = MaterialTheme.typography.bodyMedium,
            color = VizoTextSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FeatureChip("👥 120 contacts fondateurs")
            FeatureChip("📊 Analytics")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FeatureChip("💰 Commissions 20%")
            FeatureChip("⚡ Boosts")
        }
    }
}

@Composable
fun FeatureChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(VizoSurface)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 12.sp, color = VizoPrimary)
    }
}
