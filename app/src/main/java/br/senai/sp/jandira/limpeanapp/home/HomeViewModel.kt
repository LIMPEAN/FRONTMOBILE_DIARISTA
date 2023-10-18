package br.senai.sp.jandira.limpeanapp.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.login.domain.AuthRepository
import br.senai.sp.jandira.limpeanapp.login.domain.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val tokenManager : TokenRepository
) : ViewModel(){

    var state by mutableStateOf(HomeState())
        private set

    fun getToken(){
        viewModelScope.launch {
            val firstToken = tokenManager.getFirstToken()
            withContext(Dispatchers.Main){
                state = state.copy(token = firstToken)
                Log.e("token", firstToken.toString())
            }

        }
    }
}
