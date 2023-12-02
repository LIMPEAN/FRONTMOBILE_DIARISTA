package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services

import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GetPropertiesForGoogleMapUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.SendAssentment
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.GetDiaristByTokenUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.AcceptServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.FinishedService
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.GetDiaristState
import javax.inject.Inject

data class FindServicesUseCases @Inject constructor(
    val getOpenServicesUseCase: GetOpenServicesUseCase,
    val getDiaristByToken : GetDiaristByTokenUseCase,
    val acceptServiceUseCase : AcceptServiceUseCase,
    val getPropertiesForGoogleMapUseCase: GetPropertiesForGoogleMapUseCase,
    val getStartedServiceUseCase: GetStartedServiceUseCase,
    val finishedServiceUseCase : FinishedService,
    val sendAssentmentUseCase: SendAssentment
)