package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DiaristFakeRepository()  : DiaristRepository{


    private val diarists = mutableListOf<Diarist>()
    init {

        diarists.addAll(getListDiarists())

    }
    override fun getDiarists(): Flow<List<Diarist>> {
        val testeFlow = flow {
            delay(2000)
            emit(diarists)
        }
        return testeFlow
    }

    override suspend fun getDiaristById(id: Int): Diarist? {
        return diarists[id]
    }

    override suspend fun getDiaristByPhoneAndEmail(phone: String, email: String): Diarist? {
        return diarists.find { it.email == email && it.phone == phone }
    }

    override suspend fun insertDiarist(diarist: Diarist) {
        diarists.add(diarist)
    }

    override suspend fun deleteDiarist(diarist: Diarist) {
        diarists.remove(diarist)
    }

}