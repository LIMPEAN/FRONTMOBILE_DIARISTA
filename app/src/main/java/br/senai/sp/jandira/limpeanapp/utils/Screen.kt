package br.senai.sp.jandira.limpeanapp.utils

sealed class Screen(val route : String){
    object LoginScreen : Screen("login_screen")
    object FormScreen: Screen("form_screen")
}
