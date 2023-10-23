package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.ViaCepApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase.RegisterResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.InvalidDiaristException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase.AddDiarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.toDomain
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val api : ViaCepApi,
    private val addDiarist: AddDiarist
) : ViewModel() {



    var isLoading by mutableStateOf(false)
        private set


    var diarist by mutableStateOf(diaristTest)
        private set

    var address by mutableStateOf(Address())
        private set

    private val resultChannel = Channel<RegisterResult<Unit>>()
    val registerResult = resultChannel.receiveAsFlow()

    fun createProfileFormState() : ProfileFormState {
        val state = diarist
        return ProfileFormState(
            name = state.name,
            password = state.password,
            cpf = state.cpf,
            ddd = state.ddd,
            phone = state.phone,
            email = state.email
        )
    }

    fun createAddressFormState() : AddressFormState{
        val state = address
        return AddressFormState(
            cep = state.cep,
            logradouro = state.street,
            bairro =  state.district,
            estado = state.state,
            cidade = state.city,
            numero = state.number,
            isLoading = false
        )
    }

    fun onEvent(event: ProfileFormEvent) {

        when(event){
            is ProfileFormEvent.NameChanged ->{
                diarist = diarist.copy(name = event.value)
            }
            is ProfileFormEvent.CpfChanged -> {
                diarist = diarist.copy(cpf = event.value)
            }
            is ProfileFormEvent.DDDChanged -> {
                diarist = diarist.copy(ddd = event.value)

            }
            is ProfileFormEvent.EmailChanged -> {
                diarist = diarist.copy(email = event.value)
            }
            is ProfileFormEvent.PasswordChanged -> {
                diarist = diarist.copy(password = event.value)
            }
            is ProfileFormEvent.PhoneChanged -> {
                diarist = diarist.copy(phone = event.value)
            }
        }
    }

    fun onEvent(event : AddressFormEvent){
        when(event){
            is AddressFormEvent.CepChanged ->{
                val validCep = event.newCep.length <= 8
                if(validCep){
                    address = address.copy(cep = event.newCep)
                }
                if(event.newCep.length == 8){
                    findCep(event.newCep)
                }

            }

            is AddressFormEvent.NumberChanged -> {
                address = address.copy(number = event.newNumber)
            }

            is AddressFormEvent.ReceivedCepFromApi -> {
                address = event.completeAddress.toDomain()
            }
        }
    }

    fun save(address : Address){
        diarist = diarist.copy(
            address = address
        )
        Log.i("save-address", diarist.address.toString())
    }

    fun saveDiarist(){
        viewModelScope.launch {
            try {
                addDiarist(diarist)
                resultChannel.send(RegisterResult.Successful)
            }catch (e: InvalidDiaristException) {
                resultChannel.send(RegisterResult.Error("Há dados inválidos."))
            }
        }
    }

    private fun findCep(cep : String){
        runBlocking {
            val cep = api.getAddressByCep(cep)
            address = address.copy(
                street = cep.logradouro,
                complement = cep.complemento,
                district = cep.bairro,
                state = cep.uf,
                city = cep.localidade
            )
        }

    }


}
