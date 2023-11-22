package br.senai.sp.jandira.limpeanapp.core.domain.repository

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist.GetDiaristDTOX
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import kotlinx.coroutines.flow.Flow

interface DiaristRepository {

    suspend fun getDiaristByToken() : GetDiaristDTOX

    suspend fun insertDiarist(diarist: Diarist) : BaseDto

    suspend fun deleteDiarist(diarist: Diarist) : BaseDto
}