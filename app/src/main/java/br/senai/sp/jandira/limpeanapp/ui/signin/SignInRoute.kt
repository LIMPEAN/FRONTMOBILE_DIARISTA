package br.senai.sp.jandira.limpeanapp.ui.signin

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.domain.Person
import br.senai.sp.jandira.limpeanapp.domain.UserType
import br.senai.sp.jandira.limpeanapp.ui.signin.components.PersonForm

@Composable
fun SignInRoute(
    onLoginClicked: (userType: UserType, email: String, password: String) -> Unit
) {
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())


    var section = "teste"
    SignInScreen(
        title = "Test",
                content = {
                          PersonForm(person = Person(), onPersonChanged = )
        },
        onClickButton = {
        },
        nameActionButton = "Form"
    )
}

