package br.senai.sp.jandira.limpeanapp.authentication.register

sealed class RegisterRoute(val route: String) {
    object Person : RegisterRoute("person")
    object Profile : RegisterRoute("profile")
    object Address : RegisterRoute("address")
}