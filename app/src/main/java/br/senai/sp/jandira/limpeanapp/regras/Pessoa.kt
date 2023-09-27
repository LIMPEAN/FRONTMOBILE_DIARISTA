package br.senai.sp.jandira.limpeanapp.regras

import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.dados.modelos.Telefone
import java.time.LocalDate


data class Pessoa (
    val nome: String = "",
    val dataDeNascimento: LocalDate? = LocalDate.now(),
    val genero: Genero? = null,
    val cpf: String = "",
    val telefone: Telefone? = null,
)
