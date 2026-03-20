package com.vizo.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vizo.app.ui.theme.*

@Composable
fun PhoneScreen(
    onOtpSent: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.OtpSent) {
            onOtpSent(phoneNumber)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VizoBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Logo
            Text(
                text = "VIZO",
                style = MaterialTheme.typography.displayLarge,
                color = VizoPrimary
            )

            Text(
                text = "Transforme ton WhatsApp\nen machine à revenus",
                style = MaterialTheme.typography.bodyMedium,
                color = VizoTextSecondary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Champ numéro
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { viewModel.updatePhone(it) },
                label = { Text("Numéro de téléphone", color = VizoTextSecondary) },
                placeholder = { Text("97 XX XX XX", color = VizoTextMuted) },
                prefix = { Text("+229 ", color = VizoPrimary, fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VizoPrimary,
                    unfocusedBorderColor = VizoTextMuted,
                    focusedTextColor = VizoTextPrimary,
                    unfocusedTextColor = VizoTextPrimary,
                    cursorColor = VizoPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            // Erreur
            if (authState is AuthState.Error) {
                Text(
                    text = (authState as AuthState.Error).message,
                    color = VizoError,
                    fontSize = 14.sp
                )
            }

            // Bouton
            Button(
                onClick = { viewModel.sendOtp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = phoneNumber.length >= 8 && authState !is AuthState.Loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = VizoPrimary,
                    disabledContainerColor = VizoTextMuted
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (authState is AuthState.Loading) {
                    CircularProgressIndicator(
                        color = VizoBackground,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Recevoir le code SMS",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoBackground
                    )
                }
            }
        }
    }
}
