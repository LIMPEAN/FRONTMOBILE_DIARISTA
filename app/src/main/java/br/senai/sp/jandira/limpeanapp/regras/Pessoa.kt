package br.senai.sp.jandira.limpeanapp.regras

import java.util.Date

data class Pessoa (
    val nome: String = "",
    val dataDeNascimento: Date? = Date(),
    val genero: Genero? = null,
    val cpf: String? = "",
)
