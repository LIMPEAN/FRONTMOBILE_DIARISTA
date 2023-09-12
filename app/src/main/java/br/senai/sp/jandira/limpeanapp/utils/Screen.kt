package br.senai.sp.jandira.limpeanapp.utils


sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterPersonScreen : Screen("register_person_screen/{nameUserType}")
    object RegisterUserScreen : Screen("register_user_screen")
    object RegisterAdressScreen : Screen("register_address_screen")
    object RegisterHomeScreen : Screen("register_home_screen")
}
