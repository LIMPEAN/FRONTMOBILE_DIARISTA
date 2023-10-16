package br.senai.sp.jandira.limpeanapp.authentication

import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val userService: UserService
) {

    fun hello():String{
        return "Hello"
    }

}
