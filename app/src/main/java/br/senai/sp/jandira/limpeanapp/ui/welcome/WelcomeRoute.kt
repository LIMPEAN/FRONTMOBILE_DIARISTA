package br.senai.sp.jandira.limpeanapp.ui.welcome

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.login.UserType

@Composable
fun WelcomeRoute(
    onNavigateToSignIn: (userType: UserType) -> Unit,
    onNavigateToSignUp: (userType: UserType) -> Unit
) {
    val welcomeViewModel: WelcomeViewModel = viewModel(factory = WelcomeViewModelFactory())

    WelcomeScreen(
        onCreateAccount = { userType ->
            welcomeViewModel.handleContinue(
                userType = userType,
                onNavigateToSignIn = onNavigateToSignIn,
                onNavigateToSignUp = onNavigateToSignUp,
            )
        },
        onLogin = {
            welcomeViewModel.onLogin(it)
        },
    )
}
