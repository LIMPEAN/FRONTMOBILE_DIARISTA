package br.senai.sp.jandira.limpeanapp.registration.person


data class RegisterPersonState(
    val name: String = "",
    val cpf: String = "",
    val id: String = "",
    val telephone: String = "",
    val dateOfBirth: String = "",
    val gender: String = ""
)
