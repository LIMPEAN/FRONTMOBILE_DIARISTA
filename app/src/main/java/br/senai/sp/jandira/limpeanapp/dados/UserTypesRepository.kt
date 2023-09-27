package br.senai.sp.jandira.limpeanapp.dados

import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario

class UserTypesRepository {

    companion object{
        fun getAll() : List<TipoDeUsuario>{
            return listOf(
                TipoDeUsuario(0,"diarist", "Diarista"),
                TipoDeUsuario(1,"client", "Cliente")
            )
        }

    }
}