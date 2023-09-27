package br.senai.sp.jandira.limpeanapp.dados.modelos

data class Logar (
    val email : String = "",
    val senha : String = "",
    val mensagemDeErro : String? = "",
)
