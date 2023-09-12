package br.senai.sp.jandira.limpeanapp.ui.signin

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.domain.UserType

@Composable
fun SignInRoute(
    onLoginClicked: (userType: UserType, email: String, password: String) -> Unit
) {
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())


    var section = "teste"
    SignInScreen(
        title = "Test",
        content = {
            signInViewModel.handle(section)
        },
        onClickButton = {
        },
        nameActionButton = "Form"
    )
}

