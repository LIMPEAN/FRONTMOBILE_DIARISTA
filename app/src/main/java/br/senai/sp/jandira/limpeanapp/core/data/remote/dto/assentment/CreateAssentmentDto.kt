package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.assentment

import java.time.LocalDate

data class CreateAssentmentDto(
    val comment: String,
    val date: String,
    val hour: String,
    val personEvaluatedId: Int,
    val star: Int,
    val typeUser: String
)