package br.senai.sp.jandira.limpeanapp.core.domain.models

import java.time.LocalDate
import java.time.LocalTime


data class Assentment(
    val comment: String,
    val date: LocalDate,
    val hour: LocalTime,
    val personEvaluatedId: Int,
    val star: Int
)
enum class TypeUser(val id: Number, val portugueseName : String) {
    DIARIST(1,"Diarista"),
    CLIENT(2, "Cliente")
}