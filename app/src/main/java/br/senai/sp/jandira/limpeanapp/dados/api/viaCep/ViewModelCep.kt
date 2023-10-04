import android.util.Log
<<<<<<< HEAD
=======

>>>>>>> a4ca33250a94d6a93f8151f3dfe27dc281529df4
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.dados.api.viaCep.RetrofitFactoryCep
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import kotlinx.coroutines.launch

class CepViewModel(
    private val userType: TipoDeUsuario = TipoDeUsuario.pegaDiarista()
): ViewModel() {
    // Função para buscar informações do CEP



    var uiState by mutableStateOf(AddressUiState(
        userType = this.userType
    ))
        private set

    fun fetchCep(cep: String) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isFetchingApi = true,
                    showResultApi = false
                )
                val result = RetrofitFactoryCep.getCepService().getAddressByCep(cep)
                if(result.isSuccessful){
                    val data = result.body()
                    uiState = uiState.copy(
                        isFetchingApi = false,
                        viaCepAddress =  ViaCepAddress(
                            logradouro = data!!.logradouro!!,
                            bairro = data.bairro!!,
                            estado = data.uf!!,
                            cidade = data.localidade!!,

                        ),
                        showResultApi = true
                    )
                }




                Log.i("VIEWMODEL", "${result.body()}")
                Log.i("VIACEPSTATE", "$")
                // Faça algo com os dados do CEP (por exemplo, atualize um LiveData)
            } catch (e: Exception) {
                // Trate erros aqui
            }
        }
    }

    fun isClient() = userType.nomeDaApi == "client"

    fun handleNextRoute(route : String) : String{
        return when(route){
            "address" -> {
                "person"
            }
            else -> {
                "home"
            }
        }
    }
    
    fun validateAddress() : Boolean{
        return this.uiState.addressForm.validate()
    }
}



data class AddressUiState(
    val isClient: Boolean = true,
    val isDigitCpf : Boolean = true,
    val isFetchingApi : Boolean = false,
    val addressForm : FormState<TextFieldState> = FormState(
        fields = listOf(
            ChoiceState(
                name = "Tipo de Casa"
            ),
            TextFieldState(
                name = "cep"
            ),
            TextFieldState(
                name = "numero",
                validators = listOf(
                    Validators.Required("Este campo deve ser obrigatório!")
                )
            ),
            TextFieldState(
                name = "Media"
            )
        )
    ),
    val viaCepAddress : ViaCepAddress? = null,
    val showResultApi : Boolean = false,
    val userType: TipoDeUsuario
)
data class ViaCepAddress(
    val logradouro : String,
    val bairro : String,
    val estado : String,
    val cidade : String,
)
