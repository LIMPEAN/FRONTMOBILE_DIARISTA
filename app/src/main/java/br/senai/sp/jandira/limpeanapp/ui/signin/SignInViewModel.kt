package br.senai.sp.jandira.limpeanapp.ui.signin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.senai.sp.jandira.limpeanapp.domain.Gender
import br.senai.sp.jandira.limpeanapp.domain.Person
import br.senai.sp.jandira.limpeanapp.ui.signin.components.PersonForm
import java.util.Date

class SignInViewModel(
    private val state : RegistrationState
) : ViewModel() {

    var form = "PersonForm"
    @Composable
    fun handle(nameForm : String) {


    }

    @Composable
    fun anyForm() {
        Text(text = "Here's the new form")
    }

    fun changeForm(nameForm: String) {

    }
}

class SignInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(RegistrationState()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



