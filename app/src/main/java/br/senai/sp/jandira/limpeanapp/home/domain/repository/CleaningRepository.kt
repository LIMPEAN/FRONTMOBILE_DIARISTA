package br.senai.sp.jandira.limpeanapp.home.domain.repository

import br.senai.sp.jandira.limpeanapp.home.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.home.domain.models.ServiceStatus
import kotlinx.coroutines.flow.Flow

interface CleaningRepository {

    fun getScheduledCleanings() : Flow<List<Cleaning>>

    suspend fun getCleaningDetail(id: Number) : Cleaning?

}
