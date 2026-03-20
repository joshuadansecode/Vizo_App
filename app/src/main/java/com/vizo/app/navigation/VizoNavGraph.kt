package com.vizo.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vizo.app.ui.auth.OtpScreen
import com.vizo.app.ui.auth.PhoneScreen
import com.vizo.app.ui.dashboard.DashboardScreen
import com.vizo.app.ui.onboarding.OnboardingScreen
import com.vizo.app.ui.contacts.ContactsScreen
import com.vizo.app.ui.boost.BoostScreen
import com.vizo.app.ui.affiliate.AffiliateScreen
import com.vizo.app.ui.profile.ProfileScreen
import com.vizo.app.ui.subscription.SubscriptionScreen
import com.vizo.app.ui.leaderboard.LeaderboardScreen
import com.vizo.app.ui.splash.SplashScreen
import com.vizo.app.ui.create.CreateScreen
import com.vizo.app.ui.analytics.AnalyticsScreen

sealed class Screen(val route: String) {
    object Phone : Screen("phone")
    object Otp : Screen("otp/{phoneNumber}") {
        fun createRoute(phone: String) = "otp/$phone"
    }
    object Onboarding : Screen("onboarding")
    object Dashboard : Screen("dashboard")
    object Contacts : Screen("contacts")
    object Create : Screen("create")
    object Leaderboard : Screen("leaderboard")
    object Profile : Screen("profile")
    object Boost : Screen("boost")
    object Subscription : Screen("subscription")
    object Affiliate : Screen("affiliate")
    object Analytics : Screen("analytics")
    object Splash : Screen("splash")
}

@Composable
fun VizoNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Phone.route) {
            PhoneScreen(
                onOtpSent = { phone ->
                    navController.navigate(Screen.Otp.createRoute(phone))
                }
            )
        }
        composable(Screen.Otp.route) { backStack ->
            val phone = backStack.arguments?.getString("phoneNumber") ?: ""
            OtpScreen(
                phoneNumber = phone,
                onVerified = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Phone.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(Screen.Contacts.route) {
            ContactsScreen(navController = navController)
        }
        composable(Screen.Boost.route) {
            BoostScreen(navController = navController)
        }
        composable(Screen.Affiliate.route) {
            AffiliateScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.Subscription.route) {
            SubscriptionScreen(navController = navController)
        }
        composable(Screen.Leaderboard.route) {
            LeaderboardScreen(navController = navController)
        }
        composable(Screen.Create.route) {
            CreateScreen(navController = navController)
        }
        composable(Screen.Analytics.route) {
            AnalyticsScreen(navController = navController)
        }
    }
}
