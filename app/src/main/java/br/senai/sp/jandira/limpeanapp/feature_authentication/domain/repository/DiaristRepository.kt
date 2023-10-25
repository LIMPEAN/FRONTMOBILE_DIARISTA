package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Diarist
import kotlinx.coroutines.flow.Flow

interface DiaristRepository {

    fun getDiarists() : Flow<List<Diarist>>

    suspend fun getDiaristById(id : Int) : Diarist?


    suspend fun getDiaristByPhoneAndEmail(phone: String, email : String) : Diarist?


    suspend fun insertDiarist(diarist: Diarist)

    suspend fun deleteDiarist(diarist: Diarist)
}