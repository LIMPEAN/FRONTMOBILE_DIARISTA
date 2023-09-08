package br.senai.sp.jandira.limpeanapp.diarists.domain

import br.senai.sp.jandira.limpeanapp.domain.Address
import br.senai.sp.jandira.limpeanapp.domain.User
import java.util.Date

data class Diarist(
    val id: Long?,
    val userData : User,
    val address : Address
)