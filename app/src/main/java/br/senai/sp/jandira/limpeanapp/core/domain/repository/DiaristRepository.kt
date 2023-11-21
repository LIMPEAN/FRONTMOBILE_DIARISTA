package br.senai.sp.jandira.limpeanapp.core.domain.repository

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import kotlinx.coroutines.flow.Flow

interface DiaristRepository {

    fun getDiarists() : Flow<List<Diarist>>

    suspend fun getDiaristByToken(token : String) : DiaristDto



    suspend fun getDiaristByPhoneAndEmail(phone: String, email : String) : Diarist?


    suspend fun insertDiarist(diarist: Diarist)

    suspend fun deleteDiarist(diarist: Diarist)
}