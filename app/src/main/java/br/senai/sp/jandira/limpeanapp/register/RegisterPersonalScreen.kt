package br.senai.sp.jandira.limpeanapp.register

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.utils.Screen

@Composable
fun RegisterPersonalScreen(navController: NavController, viewModel: RegistrationViewModel) {
    val state = viewModel.state
    Text(text = "Hello ${state.email}!")

}


@Preview( )
@Composable
fun RegisterPersonalScreenPreview() {
    val navTest = rememberNavController()
    val testView = viewModel<RegistrationViewModel>()
    testView.onEvent(RegistrationFormEvent.EmailChanged("Felipe"))
    RegisterPersonalScreen(navController = navTest, viewModel = testView)
}