package br.senai.sp.jandira.limpeanapp.navigation

sealed class NavigationRoute(val route: String) {
    object Onboarding : NavigationRoute("onboarding")
    object Authentication : NavigationRoute("authentication")
    object Home : NavigationRoute("home")
    object Settings : NavigationRoute("settings")
}
sealed class RegisterRoute(val route: String) {
    object Person : RegisterRoute("person")
    object Profile : RegisterRoute("profile")
    object Address : RegisterRoute("address")
}