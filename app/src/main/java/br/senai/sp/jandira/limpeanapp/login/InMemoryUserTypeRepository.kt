package br.senai.sp.jandira.limpeanapp.login

class InMemoryUserTypeRepository {

        fun getAll() : List<UserType>{
            return listOf(
                UserType(1,"diarist", "Diarista"),
                UserType(1,"client", "Cliente")
            )
        }

}