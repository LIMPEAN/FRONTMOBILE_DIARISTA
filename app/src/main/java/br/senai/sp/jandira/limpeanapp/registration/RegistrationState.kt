package br.senai.sp.jandira.limpeanapp.registration


data class RegistrationState(
    val name: String = "",
    val cpf: String = "",
    val rg: String = "",
    val telephone: String = "",
    val dateOfBirth: String = "",
    val gender: String = ""
)
