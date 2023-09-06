package br.senai.sp.jandira.limpeanapp.domain

import java.util.Date

data class Person (
    val name: String,
    val birthDate: Date,
    val gender: Gender,
    val cpf: String
)
