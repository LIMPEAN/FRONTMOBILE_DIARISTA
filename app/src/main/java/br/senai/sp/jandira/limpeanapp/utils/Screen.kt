package br.senai.sp.jandira.limpeanapp.utils

sealed class Screen(val route : String){
    object RegisterUserScreen : Screen("register_user")
    object RegisterPersonalScreen : Screen("register_personal")
}
