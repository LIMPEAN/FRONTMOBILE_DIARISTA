package br.senai.sp.jandira.limpeanapp.dados

data class Logar (
    val email : String = "",
    val senha : String = "",
    val mensagemDeErro : String? = "",
)
