package br.senai.sp.jandira.limpeanapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.authentication.AuthenticationRoute
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterHost
import br.senai.sp.jandira.limpeanapp.authentication.welcome.TelaInicial
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginScreen
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterAddressScreen
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterProfileScreen
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterRoute
import com.example.compose.LimpeanAppTheme


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination : NavigationRoute
){
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ){
        navigation(startDestination = AuthenticationRoute.Welcome.route, route = NavigationRoute.Authentication.route){
            composable(AuthenticationRoute.Welcome.route){
                TelaInicial(
                    navegarParaLogin = {navHostController.navigate(AuthenticationRoute.Login.route)},
                    navegarParaCadastro ={navHostController.navigate(AuthenticationRoute.Register.route)}
                )
            }
            navigation(startDestination = RegisterRoute.Profile.route, route = AuthenticationRoute.Register.route){
                composable(route = RegisterRoute.Profile.route){
                    RegisterProfileScreen(
                        onNext = {navHostController.navigate(RegisterRoute.Person.route)}
                    )
                }
                composable(route = RegisterRoute.Person.route){
                    RegisterPersonScreen(
                        onNext = {navHostController.navigate(RegisterRoute.Address.route)}
                    )
                }
                composable(route = RegisterRoute.Address.route){
                    RegisterAddressScreen(
                        onFinish = {
                            navHostController.navigate(NavigationRoute.Home.route)
                        }
                    )
                }
            }
            composable(AuthenticationRoute.Login.route){
                LoginScreen(
                    onClickToLogin = {
                        navHostController.navigate(RegisterRoute.Address.route)
                    }
                )
            }
            composable(AuthenticationRoute.ForgotPassword.route){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "FORGOT PASSWORD SCREEN")
                    Button(onClick = {navHostController.navigate(AuthenticationRoute.Welcome.route)}) {
                        Text(text = "Come back to Welcome")
                    }
                }
        }
        }
        composable(NavigationRoute.Home.route){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column {
                    Text(text = "HOME (TUDO CERTOOOO)")
                    Button(onClick = {navHostController.navigate(NavigationRoute.Authentication.route)}) {
                        Text(text = "Volte para o Welcome")
                    }
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun TestNavHost() {
    val navHostController = rememberNavController()

    LimpeanAppTheme {
        NavigationHost(
            navHostController = navHostController ,
            startDestination = NavigationRoute.Authentication
        )
    }

}