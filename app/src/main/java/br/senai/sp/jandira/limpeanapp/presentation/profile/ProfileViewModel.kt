package br.senai.sp.jandira.limpeanapp.presentation.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.GetDiaristByTokenUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.UpdateDiaristUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.uploadPick
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.DiaristProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDiarist : GetDiaristByTokenUseCase,
    private val updateDiarist : UpdateDiaristUseCase

) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    var profile by mutableStateOf(DiaristProfile())
        private set

    var photoUrl by mutableStateOf("")
        private set

    var selectedPhoto by mutableStateOf<Uri?>(null)
        private set

    var profileDiarist by mutableStateOf(ProfileDiarist())
        private set

    var updatedState by mutableStateOf(UpdatedState())
        private set

    init {
        getDiaristFromApi()
    }

    fun onEvent(event: ProfileDiaristEvent) {
        when(event){
            is ProfileDiaristEvent.AddressFetchedChanged -> TODO()
            is ProfileDiaristEvent.BiographyChanged -> {
                profileDiarist = profileDiarist.copy(biography = event.nemValue)

            }
            is ProfileDiaristEvent.CepChanged -> TODO()
            is ProfileDiaristEvent.CpfChanged -> {
                profileDiarist = profileDiarist.copy(cpf = event.nemValue)

            }
            is ProfileDiaristEvent.EmailChanged -> {
                profileDiarist = profileDiarist.copy(email = event.nemValue)
            }
            is ProfileDiaristEvent.GenderChanged -> {
                profileDiarist = profileDiarist.copy(gender = event.newValue)

            }
            is ProfileDiaristEvent.NameChanged -> {
                profileDiarist = profileDiarist.copy(name = event.nemValue)
            }
            is ProfileDiaristEvent.NumberChanged -> {
                profileDiarist = profileDiarist.copy(event.nemValue)

            }
            is ProfileDiaristEvent.PhotoChanged -> {
                photoUrl = event.nemValue
            }
            is ProfileDiaristEvent.OnEditMode -> {
                profile = profile.copy(isEditMode = true)
            }

            is ProfileDiaristEvent.OnSaveUpdated -> updateProfile(event.context)

            ProfileDiaristEvent.OnCancelUpdated -> {
                profile = profile.copy(isEditMode = false)
            }

            is ProfileDiaristEvent.OnPhotoSelected -> {
                selectedPhoto = event.uri
            }
        }
    }

    private fun getDiaristFromApi() {
        getDiarist().onEach {result ->
            when (result){
                is Resource.Success -> {
                    profile = DiaristProfile(
                        diarist = result.data?: Diarist()
                    )
                    val diarist = result.data?: Diarist()
                    photoUrl = diarist.photo!!
                    profileDiarist = ProfileDiarist(
                        name = diarist.name,
                        cpf = diarist.cpf,
                        email = diarist.email,
                        gender = diarist.gender,
                        biography = diarist.biography,
                        address = if(diarist.address.isEmpty()){
                            Address() } else{ diarist.address.first()},
                        phones = diarist.phones
                    )
                }
                is Resource.Error -> {
                    profile = DiaristProfile(
                        error = result.message?: "Erro ao carregar diarista."
                    )
                }
                is Resource.Loading -> {
                    profile = DiaristProfile(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    suspend fun apagarDiarista() {
//        try {
//            api.deleteDiarist()
//            Log.i("ProfileViewModel", "Diarista excluído com sucesso")
//        } catch (e: Exception) {
//            // Trate a exceção de maneira apropriada
//            Log.e("ProfileViewModel", "Erro ao excluir diarista: ${e.message}")
//        }
    }

    suspend fun atualizarDiarista(diaristDto: DiaristDto) {
//        try {
//            // Substitua a linha a seguir pela lógica real de atualização da API
//            api.updateDiarist(diaristDto)
//            Log.i("ProfileViewModel", "Diarista atualizado com sucesso")
//        } catch (e: Exception) {
//            // Trate a exceção de maneira apropriada
//            Log.e("ProfileViewModel", "Erro ao atualizar diarista: ${e.message}")
//        }
    }



    private fun updateProfile(
        context : Context
    ){
        profile = profile.copy(isEditMode = false)
        selectedPhoto?.let {
            uploadPick(uri = it,context){newPhoto ->
                photoUrl = newPhoto
                profileDiarist = profileDiarist.copy(photoUrl = newPhoto)
            }
        }
        val updatedDiarist = profile.diarist.copy(
            name = profileDiarist.name,
            photo = photoUrl,
            cpf = profileDiarist.cpf,
            email = profileDiarist.email,
            gender = profileDiarist.gender,
            biography = profileDiarist.biography,
            address = listOf(profileDiarist.address),
            phones = profileDiarist.phones,
        )
        updateDiarist(updatedDiarist).onEach { result ->
            when(result){
                is Resource.Success -> {
                    updatedState = UpdatedState(
                        message = result.data?.message?: "Atualizado com sucesso."
                    )
                }
                is Resource.Error -> {
                    updatedState = UpdatedState(
                        message = result.message?: "Erro ao atualizar diarista."
                    )
                }
                is Resource.Loading -> {
                    updatedState = UpdatedState(
                        isLoading = true
                    )
                }
            }
            getDiaristFromApi()
        }.launchIn(viewModelScope)

        Log.i("UPDATE_VIEWMODEL", profileDiarist.toString())
    }
}