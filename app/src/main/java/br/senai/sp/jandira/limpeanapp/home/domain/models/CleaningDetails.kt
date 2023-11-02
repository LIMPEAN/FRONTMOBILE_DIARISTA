package br.senai.sp.jandira.limpeanapp.home.domain.models


data class CleaningDetails(
    val questions : List<Question>,
    val roomsQuantity : List<RoomQuantity>
)
data class Question(
    val question : String,
    val answer : Boolean,
)
