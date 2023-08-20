package br.senai.sp.jandira.limpeanapp.registration.person

import android.text.format.DateFormat


data class RegistrationPersonState(
    val photoUri: String? = null,
    val name: String = "",
    val cpf: String = "",
    val date: DateFormat = DateFormat(),
    val gender: String = "",

    val photoError: String? = null,
    val nameError: String? = null,
    val dateError: String? = null,
    val cpfError: String? = null,
    val genderError: String? = null,
)
