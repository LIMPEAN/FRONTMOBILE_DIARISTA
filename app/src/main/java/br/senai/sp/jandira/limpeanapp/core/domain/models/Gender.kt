package br.senai.sp.jandira.limpeanapp.core.domain.models

enum class Gender(val id: Int, val nome: String) {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    OUTROS(3, "Outros")
}
