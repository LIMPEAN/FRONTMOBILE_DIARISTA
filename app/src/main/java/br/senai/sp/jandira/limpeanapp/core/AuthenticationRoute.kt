package br.senai.sp.jandira.limpeanapp.core

sealed class AuthenticationRoute(val route: String) {
    object Welcome : AuthenticationRoute("welcome")
    object Register : AuthenticationRoute("register")
    object Login : AuthenticationRoute("login")
    object ForgotPassword : AuthenticationRoute("forgot_password")
}