package br.senai.sp.jandira.limpeanapp.core.domain.models

data class Session(
    val user: User,
    val token: String,
    val expiresAt: Long,

)
data class User(
    val id: Number,
    val email: String,
//    val name : String
)


