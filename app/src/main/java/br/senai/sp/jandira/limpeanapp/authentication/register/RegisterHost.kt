package br.senai.sp.jandira.limpeanapp.authentication.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute


@Composable
fun RegisterHost(
    navHostController: NavHostController,
    startDestination : RegisterRoute
){
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route)
    {
        composable(RegisterRoute.Profile.route){
            RegisterProfileScreen(onNext = {navHostController.navigate(RegisterRoute.Person.route)})
        }
        composable(RegisterRoute.Person.route){
            RegisterPersonScreen(onNext = {navHostController.navigate(RegisterRoute.Address.route)})
        }
        composable(RegisterRoute.Address.route){
            RegisterAddressScreen(onFinish = {navHostController.navigate(NavigationRoute.Home.route)})
        }
    }

}
@Preview
@Composable
fun RegisterTest(){
    val navHostController = rememberNavController()
    RegisterHost(
        navHostController = navHostController,
        startDestination = RegisterRoute
            .Profile
    )
}
