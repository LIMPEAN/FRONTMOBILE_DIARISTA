package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

data class UpdateStatusDto(
    val status : String,
    val message: StatusBase
)
data class StatusBase(
    val status: String ,
    val message: String
)
