package br.senai.sp.jandira.limpeanapp.presentation.profile

import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.core.domain.models.Phone

data class ProfileState(
    val profileDiarist : ProfileDiarist = ProfileDiarist(),
    val isLoading: Boolean = false,
    val error : String? = null
)
data class ProfileDiarist(
    val photoUrl : String = "",
    val name : String = "",
    val cpf : String = "",
    val email : String = "",
    val gender : Gender = Gender.OUTROS,
    val biography: String? = "Nenhuma biografia encontrada.",
    val address : Address = Address(),
    val phones : List<Phone> = listOf(Phone("11", "97856565"))
)

fun Diarist.toProfileDiarist() : ProfileDiarist {
    return ProfileDiarist(
        photoUrl = photo?: "",
        name = name,
        cpf = cpf,
        email = email,
        gender = gender,
        biography = biography,
        address = if(address.isNotEmpty()){address.first()
        }else{ Address() },
        phones = phones.map {
            Phone(
                ddd = it.ddd,
                number = it.number)
        }
    )

}
