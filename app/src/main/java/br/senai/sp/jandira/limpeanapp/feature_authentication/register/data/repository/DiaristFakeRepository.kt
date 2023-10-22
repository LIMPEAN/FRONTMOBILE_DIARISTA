package br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class DiaristFakeRepository : DiaristRepository{
    override fun getDiarists(): Flow<List<Diarist>> {
        return flow {
            emit(listOf(
                Diarist(
                    id = 1,
                    name = "ana",
                    cpf = "123456789089",
                    phone ="11 91234-5678",
                    email = "ana@gmail.com",
                    password = "123456",
                    dateOfBirth = LocalDate.of(2000,10,4),
                    address = Address(),
                    photo = null,
                    gender = Gender.FEMININO,
                    ddd = "10",

                ),Diarist(
                    id = 2,
                    name = "Giovani",
                    cpf = "123456789089",
                    phone ="11 91234-5678",
                    email = "ana@gmail.com",
                    password = "123456",
                    dateOfBirth = LocalDate.of(2000,10,4),
                    address = Address(),
                    photo = null,
                    gender = Gender.MASCULINO,
                    ddd = "10"
                ),
            ))
        }
    }

    override suspend fun getDiaristById(id: Int): Diarist? {
        return Diarist(
            "",
            "",
            "10",
            "",
            "",
            "",
            LocalDate.of(2000,10,12),
            photo = null,
            address = Address(),
            gender = Gender.FEMININO,
            id = null
        )
    }

    override suspend fun getDiaristByPhoneAndEmail(phone: String, email: String): Diarist? {
        TODO("Not yet implemented")
    }

    override suspend fun insertDiarist(diarist: Diarist) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiarist(diarist: Diarist) {
        TODO("Not yet implemented")
    }
}