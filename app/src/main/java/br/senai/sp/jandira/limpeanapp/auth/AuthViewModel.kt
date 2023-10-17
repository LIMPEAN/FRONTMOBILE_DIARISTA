package br.senai.sp.jandira.limpeanapp.auth

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.auth.data.repository.AuthRepository
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
