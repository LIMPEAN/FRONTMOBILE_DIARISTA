package br.senai.sp.jandira.limpeanapp.dados

import br.senai.sp.jandira.limpeanapp.regras.UserType

class UserTypesRepository {

    companion object{
        fun getAll() : List<UserType>{
            return listOf(
                UserType(0,"diarist", "Diarista"),
                UserType(1,"client", "Cliente")
            )
        }

    }
}