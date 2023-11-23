package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import java.time.LocalDate
import java.time.LocalTime

data class AssentmentDto(
    val comment: String,
    val date: String,
    val hour: String,
    val personEvaluatedId: Int,
    val star: Int,
    val typeUser: String
)
fun AssentmentDto.toAssentment() : Assentment {
    return Assentment(
        comment = comment,
        date = LocalDate.parse(date),
        hour = LocalTime.parse(hour),
        personEvaluatedId = personEvaluatedId,
        star = star
    )
}