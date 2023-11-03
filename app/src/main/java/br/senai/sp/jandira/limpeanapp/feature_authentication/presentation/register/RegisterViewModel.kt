package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.ViaCepApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.RegisterResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.AddDiarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.toDomain
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormState
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
        return ProfileFormState(
            name = diarist.name,
            password = diarist.password,
            cpf = diarist.cpf,
            ddd = diarist.ddd,
            phone = diarist.phone,
            email = diarist.email
        )
    }

    fun createAddressFormState() : AddressFormState {
        return AddressFormState(
            cep = address.cep,
            logradouro = address.street,
            bairro =  address.district,
            estado = address.state,
            cidade = address.city,
            numero = address.number,
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
        Log.i("save-address", diarist.toString())
    }
    fun save(profileState: ProfileFormState){
       diarist = diarist.copy(
           name = profileState.name,
           cpf = profileState.cpf,
           phone = profileState.phone,
           ddd = profileState.ddd,
           email = profileState.email,
           password = profileState.password
       )
        Log.i("save-diarist", diarist.toString())
    }

    fun saveDiarist(){
        viewModelScope.launch {
            try {
                addDiarist(diarist)
                resultChannel.send(RegisterResult.Successful)
            }
            catch (e: Exception) {
                resultChannel.send(
                    RegisterResult.Error(e.message!!)
                )
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
