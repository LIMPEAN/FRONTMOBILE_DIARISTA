package br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.TokenResponse
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Session
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.User
import kotlin.math.exp

fun TokenResponse.toSession(): Session{
    return Session(
        user = User(
            id = this.id,
            email = this.email
        ),
        token = this.token,
        expiresAt = 100000
    )
}