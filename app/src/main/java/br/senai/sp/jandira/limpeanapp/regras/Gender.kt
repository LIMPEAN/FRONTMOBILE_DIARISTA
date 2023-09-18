package br.senai.sp.jandira.limpeanapp.regras

data class Gender (
    val id: Long,
    val name: String,
    val symbol: String
) {
    companion object {
        fun values(): List<Gender> {
            return listOf(
                Gender(0, "Masculino", "M"),
                Gender(1, "Feminino", "F"),
                Gender(2, "Outro", "O"),
                Gender(3, "Prefiro Não Informar", "N"),
            )
        }
    }
}
