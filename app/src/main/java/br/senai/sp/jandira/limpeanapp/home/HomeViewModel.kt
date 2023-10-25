package br.senai.sp.jandira.limpeanapp.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.SessionCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val sessionCache: SessionCache
) : ViewModel(){

    var state by mutableStateOf(HomeState())
        private set

    val session get() = runBlocking {
        sessionCache.getActiveSession()
    }

}
