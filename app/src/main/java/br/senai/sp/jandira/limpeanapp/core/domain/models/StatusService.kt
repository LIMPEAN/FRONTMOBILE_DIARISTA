package br.senai.sp.jandira.limpeanapp.core.domain.models

enum class StatusService(val codigo: Number, val description: String) {
    EM_ABERTO(1, "Em aberto"),
    AGENDADO(2, "Agendado"),
    EM_ANDAMENTO(3, "Em andamento"),
    FINALIZADO(4, "Finalizado"),
    CANCELADO(5, "Cancelado")
}
