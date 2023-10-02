package br.senai.sp.jandira.limpeanapp.authentication.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.authentication.AuthenticationRoute
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterAddressScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterProfileScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel


@Composable
fun RegisterHost(
    navHostController: NavHostController,
    startDestination : RegisterRoute
){
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route){
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
        startDestination = RegisterRoute.Profile
    )
}
