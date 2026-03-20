package com.vizo.app.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vizo.app.navigation.Screen
import com.vizo.app.ui.theme.VizoBackground
import com.vizo.app.ui.theme.VizoPrimary
import com.vizo.app.ui.theme.VizoSurface
import com.vizo.app.ui.theme.VizoTextSecondary

import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun VizoBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = VizoSurface) {
        val items = listOf(
            Triple(Screen.Dashboard.route, "🏠", "Accueil"),
            Triple(Screen.Contacts.route, "👥", "Contacts"),
            Triple(Screen.Create.route, "✏️", "Créer"),
            Triple(Screen.Leaderboard.route, "🏆", "Classement"),
            Triple(Screen.Profile.route, "👤", "Profil")
        )
        items.forEach { (route, icon, label) ->
            val isSelected = currentRoute == route
            NavigationBarItem(
                selected = isSelected,
                onClick = { 
                    if (!isSelected) {
                        navController.navigate(route) {
                            popUpTo(Screen.Dashboard.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Text(icon, fontSize = 20.sp) },
                label = { Text(label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = VizoPrimary,
                    selectedTextColor = VizoPrimary,
                    unselectedIconColor = VizoTextSecondary,
                    unselectedTextColor = VizoTextSecondary,
                    indicatorColor = VizoPrimary.copy(alpha = 0.1f)
                )
            )
        }
    }
}
