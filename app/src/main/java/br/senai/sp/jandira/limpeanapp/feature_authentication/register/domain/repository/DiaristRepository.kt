package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.ErrorRepositoryException
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface DiaristRepository {

    fun getDiarists() : Flow<List<Diarist>>

    suspend fun getDiaristById(id : Int) : Diarist?


    suspend fun getDiaristByPhoneAndEmail(phone: String, email : String) : Diarist?


    suspend fun insertDiarist(diarist: Diarist)

    suspend fun deleteDiarist(diarist: Diarist)
}