package br.senai.sp.jandira.limpeanapp.regras

import java.util.Date

data class Pessoa (
    val nome: String? = null,
    val dataDeNascimento: Date? = null,
    val genero: Genero? = null,
    val cpf: String? = null,
    val rg: String? = null
)
