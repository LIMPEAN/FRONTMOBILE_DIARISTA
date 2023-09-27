package br.senai.sp.jandira.limpeanapp.dados.repositorios

import androidx.compose.runtime.Immutable
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario


sealed class User {
    @Immutable
    data class LoggedInUser(val email: String, val userType: TipoDeUsuario) : User()
    object Diarist : User()
    object Client : User()
   
}

object UserRepository {
    private var _user: User = User.Diarist
    val user: User
        get() = _user

    @Suppress("UNUSED_PARAMETER")
    fun signIn(userType: TipoDeUsuario, email: String, password: String) {
        _user = User.LoggedInUser(userType = userType, email = email)
    }

    @Suppress("UNUSED_PARAMETER")
    fun signUp(userType : TipoDeUsuario, email: String, password: String) {
        _user = User.LoggedInUser(email = email, userType = userType)
    }

}