package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases

import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.AuthRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() : AuthResult<Unit> {
        return authRepository.authenticate()
    }

}