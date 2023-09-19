package br.senai.sp.jandira.limpeanapp.dados

import br.senai.sp.jandira.limpeanapp.regras.Person
import br.senai.sp.jandira.limpeanapp.regras.Phone

data class Usuario(
    val dadosDePessoa : Person? = null,
    val email: String? = null,
    val senha: String? = null,
    val telefone: Phone? = null,
    var biografia: String? = null
)
