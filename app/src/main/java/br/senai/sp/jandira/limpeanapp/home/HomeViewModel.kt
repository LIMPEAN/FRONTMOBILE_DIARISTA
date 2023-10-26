package br.senai.sp.jandira.limpeanapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.home.data.remote.DiaristApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val diaristApi: DiaristApi
) : ViewModel() {

    var profileDiarist by mutableStateOf("")
        private set

    fun findDiarist(){
        viewModelScope.launch {
            profileDiarist = diaristApi.getDiarist().toString()
        }
    }
}