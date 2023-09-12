package br.senai.sp.jandira.limpeanapp.data

import androidx.compose.runtime.Immutable
import br.senai.sp.jandira.limpeanapp.domain.UserType


sealed class User {
    @Immutable
    data class LoggedInUser(val email: String, val userType: UserType) : User()
    object Diarist : User()
    object Client : User()
   
}

object UserRepository {
    private var _user: User = User.Diarist
    val user: User
        get() = _user

    @Suppress("UNUSED_PARAMETER")
    fun signIn(userType: UserType, email: String, password: String) {
        _user = User.LoggedInUser(userType = userType, email = email)
    }

    @Suppress("UNUSED_PARAMETER")
    fun signUp(userType : UserType, email: String, password: String) {
        _user = User.LoggedInUser(email = email, userType = userType)
    }

}