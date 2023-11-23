package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum

data class StatusService(
    val data_hora: String,
    val status: String
)

fun StatusService.toTypeEnum() : TypeCleaningEnum{
    val type = TypeCleaningEnum.values().find {
        it.nameApi == status
    }
    return type?: TypeCleaningEnum.PADRAO
}