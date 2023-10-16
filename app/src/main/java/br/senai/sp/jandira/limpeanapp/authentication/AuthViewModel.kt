package br.senai.sp.jandira.limpeanapp.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.dados.repositorios.User
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


//A implementar : Para usar na rota "authentication"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun helloFromRepository(): String {
        return authRepository.hello()
    }

}
