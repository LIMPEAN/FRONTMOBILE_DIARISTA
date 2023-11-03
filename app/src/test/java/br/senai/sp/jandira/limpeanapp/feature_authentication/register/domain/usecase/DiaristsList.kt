package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import java.time.LocalDate

fun getListDiarists() : List<Diarist> {
    return listOf(
        Diarist(
            id = 1,
            name = "ana",
            cpf = "123456789089",
            phone ="11 91234-5678",
            email = "ana@gmail.com",
            password = "123456",
            dateOfBirth = LocalDate.of(2000,10,4),
            address = addressFake,
            photo = null,
            gender = Gender.FEMININO,
            biography = null
        ),
        Diarist(
            id = 2,
            name = "Giovani",
            cpf = "123456789089",
            phone ="11 91234-5678",
            email = "ana@gmail.com",
            password = "123456",
            dateOfBirth = LocalDate.of(2000,10,4),
            address = addressFake,
            photo = null,
            gender = Gender.MASCULINO,
            biography = null
        ),
        )
}

val addressFake = Address("","",
    "","", "", "", null)
