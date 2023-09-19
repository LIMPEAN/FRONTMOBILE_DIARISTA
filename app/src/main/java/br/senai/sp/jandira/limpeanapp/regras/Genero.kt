package br.senai.sp.jandira.limpeanapp.regras

data class Genero (
    val id: Long,
    val nome: String,
    val simbolo: String
) {
    companion object {
        fun values(): List<Genero> {
            return listOf(
                Genero(0, "Masculino", "M"),
                Genero(1, "Feminino", "F"),
                Genero(2, "Outro", "O"),
                Genero(3, "Prefiro Não Informar", "N"),
            )
        }
    }
}
