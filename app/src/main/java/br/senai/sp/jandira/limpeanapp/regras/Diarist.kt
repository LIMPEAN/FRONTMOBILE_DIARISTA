package br.senai.sp.jandira.limpeanapp.regras

import br.senai.sp.jandira.limpeanapp.dados.User

data class Diarist(
    val id: Long?,
    val userData : User,
    val address : Address
)