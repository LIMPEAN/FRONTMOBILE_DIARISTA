package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toCleaning
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CleaningRepositoryImpl @Inject constructor(
    private val api : DiaristApi
) : CleaningRepository{
    override fun getOpenServices(): Flow<List<Cleaning>> {
        return flow {
            while (true){
//                val servicesDto = api.getOpenServices()
//                val services = servicesDto.data.map {
//                    it.service.toCleaning()
//                }
//                Log.i("SERVICES", services.toString())
                emit(fakeCleanings)
                delay(5000)
            }
        }
    }

    override fun getScheduledCleanings(): Flow<List<Cleaning>> {
        return flow {
            while (true){
//                val servicesDto = api.getOpenServices()
//                Log.i("SERVICES", servicesDto.toString())
//                val services = servicesDto.data.map { it.service.toCleaning() }
                emit(fakeCleanings)
                delay(5000)
            }
        }
    }

    override suspend fun getCleaningDetail(id: Number): Cleaning? {
        return Cleaning(1)
    }
}