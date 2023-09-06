package br.senai.sp.jandira.limpeanapp.domain

data class User(
    val personData : Person,
    val email: String,
    val password: String,
    val phone: Phone,
    val biography: String?
)
