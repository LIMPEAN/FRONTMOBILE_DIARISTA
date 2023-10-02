package br.senai.sp.jandira.limpeanapp.navigation

sealed class NavigationRoute(val route: String) {
    object Onboarding : NavigationRoute("onboarding")
    object Authentication : NavigationRoute("authentication")
    object Home : NavigationRoute("home")
    object Settings : NavigationRoute("settings")
}
