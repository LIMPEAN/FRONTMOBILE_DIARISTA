package br.senai.sp.jandira.limpeanapp.telas.cadastro


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeUsuario
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.Perfil
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
class SignInViewModel(
    private val repositorioDeUsuario: RepositorioDeUsuario,
) : ViewModel(){


    var generosState by mutableStateOf(Genero.values())
        private set
    var cadastroState by mutableStateOf(CadastroState())
        private set

    val personFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Nome",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo não pode estar vazio!"),
                    Validators.Max(100, "O nome pode ter até 100 caracteres.")
                )
            ),
            TextFieldState(
                name = "Cpf",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.MinValue(20, "Minímo : O cpf deve ter 20 caracteres!"),
                    Validators.MaxValue(20, "Máximo : O cpf deve ter 20 caracteres!"),
                    Validators.Custom("Este Cpf não é um cpf válido!") {
                        validarCpf(it.toString())
                    }
                )
            ),
            TextFieldState(
                name = "Telefone",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.Phone("Deve ser um telefone válido!")
                )
            ),
            TextFieldState(
                name = "Data de Nascimento",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.Custom("Deve ser uma data de nascimento válida!"){
                        validarData(it.toString())
                    }
                )
            ),
            ChoiceState(
                name = "Generos",
                validators = listOf(
                    Validators.Required("Deve estar selecionado uma opção!"),
                )
            )
        )
    )

    fun cadastrarUsuario(userData: UserApi) {
        repositorioDeUsuario.adicionarUsuario(userData, object : RepositorioDeUsuario.RepositorioDeUsuarioCallback {
            override fun onSuccess(message: String) {
                cadastroState = cadastroState.copy(status = message)
            }

            override fun onError(errorMessage: String) {
               cadastroState = cadastroState.copy(status = errorMessage)
            }
        })
    }

    fun validarCpf(cpf : String) : Boolean{
        return true
    }
    fun validarData(data : String) : Boolean {
        return true
    }


    fun alterarTipoDeUsuario(tipoDeUsuario : TipoDeUsuario){
        cadastroState = cadastroState.copy(tipoDeUsuario = tipoDeUsuario)
    }

    fun salvarPerfil(perfil : Perfil){
        cadastroState = cadastroState.copy(userData = UserApi(
            photoUrl = perfil.fotoDePerfil.toString(),
            biography = perfil.biografia,
            email = perfil.email,
            password = perfil.senha,
            averagePrice = perfil.media
        ))
    }



//    fun createUser(usuario : ti){
//        viewModelScope.launch {
////            val serializado = Gson().toJson(usuario.enderecoLocal, Endereco::class.java)
//            val enderecoFinal = JsonObject().apply {
//                addProperty("state", usuario.enderecoLocal!!.numeroDoEstado)
//                addProperty("city", "${usuario.enderecoLocal!!.cidade}")
//                addProperty("cep", "${usuario.enderecoLocal!!.cep}")
//                addProperty("publicPlace", "${usuario.enderecoLocal!!.rua}")
//                addProperty("complement", "${usuario.enderecoLocal!!.complemento}")
//                addProperty("district", "${usuario.enderecoLocal!!.bairro}")
//                addProperty("houseNumber", "${usuario.enderecoLocal!!.numeroDaCasa}")
//            }
//            val body = JsonObject().apply {
//                addProperty("typeUser", "${usuario.nomeTipoUsuario}")
//                addProperty("email", "${usuario.email}")
//                addProperty("password", "${usuario.senha}")
//                addProperty("nameUser", "${usuario.nomeDaPessoa}")
//                addProperty("photoUser", "${usuario.fotoUri}")
//                addProperty("phone", "${usuario.telefone}")
//                addProperty("ddd", "${usuario.ddd}")
//                addProperty("birthDate", "${usuario.dataDeNascimento}")
//                addProperty("idGender", usuario.idDoGenero)
//                addProperty("cpf", "${usuario.cpf}")
//                addProperty("biography", "${usuario.biografia}")
//                addProperty("averagePrice", "${usuario.precoMedio}")
//            }
//            body.add("address",enderecoFinal)
//
//            Log.i("DS3M", "$body")
////            val teste = Gson().toJson(usuario,DiaristaApi::class.java)
////            Log.i("GSON", teste)
//            val result = apiService().createUser(body)
//
//
//            if (result.isSuccessful) {
//                Log.e("CREATEDATA", "${result.body()}")
//                Log.i("STATUS-RESULT-OBJECT", result.message())
//                Log.i("STATUS-RESULT-OBJECT", "${result.code()}")
//
//            } else {
//                Log.e("CREATEDATA", "${result.message()}")
//                Log.i("STATUS-RESULT-OBJECT", result.message())
//                Log.i("STATUS-RESULT-OBJECT", "${result.code()}")
//            }
//
//
//
//
//        }
//    }

    companion object {
        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeUsuario = RepositorioDeUsuario(RetrofitFactory.getUserService())
                SignInViewModel(repositorioDeUsuario)
            }
        }
//        fun fazerIntegracaoFake() : ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val repositorioDeUsuario = RepositorioDeUsuario(apiService = FakeApiService())
//                IntegracaoDeCadastro(repositorioDeUsuario)
//            }
//        }


    }


}
data class CadastroState(
    val status : String = "",
    val tipoDeUsuario : TipoDeUsuario? = null,
    val userData : UserApi? = null
)