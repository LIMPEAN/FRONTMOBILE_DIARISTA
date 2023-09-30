package br.senai.sp.jandira.limpeanapp.telas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.dados.repositorios.User


//A implementar : Para usar na rota "authentication"


class AuthViewModel(
): ViewModel() {

    var user by mutableStateOf("")
        private set

}