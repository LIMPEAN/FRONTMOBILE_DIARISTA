package br.senai.sp.jandira.limpeanapp.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.senai.sp.jandira.limpeanapp.data.UserRepository
import br.senai.sp.jandira.limpeanapp.domain.UserType


class WelcomeViewModel(
    private val userRepository : UserRepository
) : ViewModel() {

    fun handleContinue(
        userType: UserType,
        onNavigateToSignIn: (userType: UserType) -> Unit,
        onNavigateToSignUp: (userType: UserType) -> Unit,
    ) {
        onNavigateToSignIn(userType)
    }
    fun onLogin(userType: UserType){

    }

}

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

