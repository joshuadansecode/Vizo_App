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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vizo.app.ui.theme.*

@Composable
fun OtpScreen(
    phoneNumber: String,
    onVerified: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()
    var otpValue by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        if (authState is AuthState.Verified) {
            onVerified()
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
            Text(
                text = "VIZO",
                style = MaterialTheme.typography.displayLarge,
                color = VizoPrimary
            )

            Text(
                text = "Code envoyé au",
                style = MaterialTheme.typography.bodyMedium,
                color = VizoTextSecondary
            )

            Text(
                text = "+229 $phoneNumber",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = VizoTextPrimary
            )

            // Champ OTP
            OutlinedTextField(
                value = otpValue,
                onValueChange = {
                    if (it.length <= 6) otpValue = it
                },
                label = { Text("Code à 6 chiffres", color = VizoTextSecondary) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VizoPrimary,
                    unfocusedBorderColor = VizoTextMuted,
                    focusedTextColor = VizoTextPrimary,
                    unfocusedTextColor = VizoTextPrimary,
                    cursorColor = VizoPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    letterSpacing = 8.sp
                )
            )

            // Erreur
            if (authState is AuthState.Error) {
                Text(
                    text = (authState as AuthState.Error).message,
                    color = VizoError,
                    fontSize = 14.sp
                )
            }

            // Bouton vérifier
            Button(
                onClick = { viewModel.verifyOtp(otpValue) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = otpValue.length == 6 && authState !is AuthState.Loading,
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
                        text = "Vérifier le code",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = VizoBackground
                    )
                }
            }

            // Renvoyer
            TextButton(onClick = { viewModel.sendOtp() }) {
                Text(
                    text = "Renvoyer le code",
                    color = VizoPrimary,
                    fontSize = 14.sp
                )
            }
        }
    }
}
