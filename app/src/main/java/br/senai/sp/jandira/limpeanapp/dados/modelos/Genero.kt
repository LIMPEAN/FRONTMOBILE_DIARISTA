package br.senai.sp.jandira.limpeanapp.dados.modelos

enum class Genero(val id: Int, val etiqueta: String) {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    OUTRO(3, "Outro"),
    PREFIRO_NAO_INFORMAR(4, "Prefiro não informar")
}
