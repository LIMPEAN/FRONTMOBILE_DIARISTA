package br.senai.sp.jandira.limpeanapp.login

class InMemoryUserTypeRepository {


    companion object {
        fun getAll(): List<UserType> {
            return listOf(
                UserType(0, "diarist", "Diarista"),
                UserType(1, "client", "Cliente")
            )
        }
    }
}