package br.senai.sp.jandira.limpeanapp.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.home.data.remote.DiaristApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val diaristApi: DiaristApi
) : ViewModel() {

    var diarist by mutableStateOf<Diarist?>(null)
        private set

    var diaristName by mutableStateOf<String?>(null)
        private set

    fun findDiarist(){
        viewModelScope.launch {
            diaristName = diaristApi.getDiarist().data.name
        }
    }
}