package br.senai.sp.jandira.limpeanapp.regras

import java.util.Date

data class Person (
    val name: String? = null,
    val dateOfBirth: Date? = null,
    val gender: Gender? = null,
    val cpf: String? = null,
    val rg: String? = null
)
