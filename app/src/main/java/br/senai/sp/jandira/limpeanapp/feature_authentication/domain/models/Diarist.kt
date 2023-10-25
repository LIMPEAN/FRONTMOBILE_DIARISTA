package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models

import android.net.Uri
import java.time.LocalDate

data class Diarist(
    val name: String,
    val cpf : String,
    val ddd : String,
    val phone: String,
    val email: String,
    val password: String,
    val dateOfBirth : LocalDate,
    val photo : Uri?,
    val gender : Gender,
    val biography : String? = null,
    val address : Address,
    val id : Int?
){
    companion object {
        val genders = listOf(Gender.MASCULINO, Gender.FEMININO, Gender.OUTROS)
    }
}

