package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.AssentmentDto
import java.time.LocalDate

data class Diarist(
    val name: String ="",
    val cpf : String = "",
    val phones : List<Phone> = emptyList(),
    val email: String = "",
    val password: String = "",
    val dateOfBirth : LocalDate = LocalDate.now(),
    val photo : String? = null,
    val gender : Gender = Gender.OUTROS,
    val biography : String? = null,
    val address : List<Address> = emptyList(),
    val assentments : List<Assentment> = emptyList(),
    val id : Int? = null
){
    companion object {
        val genders = listOf(Gender.MASCULINO, Gender.FEMININO, Gender.OUTROS)
    }
}
data class Phone (
    val ddd : String = "",
    val number : String = "",
)

