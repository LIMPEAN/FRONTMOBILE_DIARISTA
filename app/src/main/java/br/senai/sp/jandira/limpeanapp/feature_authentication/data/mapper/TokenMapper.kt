package br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.TokenResponse
import br.senai.sp.jandira.limpeanapp.core.domain.models.Session
import br.senai.sp.jandira.limpeanapp.core.domain.models.User

fun TokenResponse.toSession(): Session {
    return Session(
        user = User(
            id = this.id,
            email = this.email,
        ),
        token = this.token,
        expiresAt = 100000
    )
}