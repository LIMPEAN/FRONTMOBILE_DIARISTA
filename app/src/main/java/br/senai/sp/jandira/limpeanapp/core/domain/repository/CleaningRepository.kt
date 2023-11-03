package br.senai.sp.jandira.limpeanapp.core.domain.repository

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import kotlinx.coroutines.flow.Flow

interface CleaningRepository {

    fun getScheduledCleanings() : Flow<List<Cleaning>>

    suspend fun getCleaningDetail(id: Number) : Cleaning?

}
