package br.senai.sp.jandira.limpeanapp.core.domain.usecases

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AcceptServiceUseCase @Inject constructor(
    private val repository: CleaningRepository
) {
    operator fun invoke() : Flow<Resource<Cleaning>> = flow{

    }
}
