package br.senai.sp.jandira.limpeanapp.telas.cadastro


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.dados.modelos.Telefone
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeUsuario
import br.senai.sp.jandira.limpeanapp.regras.MaskTransformation
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.Perfil
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.SelectState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import org.jetbrains.annotations.TestOnly

class SignInViewModel(
    private val repositorioDeUsuario: RepositorioDeUsuario,

) : ViewModel(){



    var uiState by mutableStateOf(PersonFormState())
        private set
    var generosState by mutableStateOf(Genero.values())
        private set
    var cadastroState by mutableStateOf(CadastroState())
        private set

    var user by mutableStateOf(UserApi())
        private set



    private fun getGenderId(genderName : String) : Int{
        return when (genderName){
            Genero.MASCULINO.etiqueta -> Genero.MASCULINO.id
            Genero.FEMININO.etiqueta -> Genero.FEMININO.id
            Genero.OUTRO.etiqueta -> Genero.OUTRO.id
            Genero.PREFIRO_NAO_INFORMAR.etiqueta -> Genero.PREFIRO_NAO_INFORMAR.id
            else -> {return 0}
        }
    }

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

    fun getVisualTransformation(type: String) : MaskTransformation? {
        return when(type){
            "cpf" ->  MaskTransformation.CPF
            "dateBirth" -> MaskTransformation.DATE
            "phone" -> MaskTransformation.PHONE
            else -> null
        }
    }


    fun onSelectGender(genderName : String){
        val genderId = getGenderId(genderName)
        user = user.copy(
            genderId = genderId
        )
        val genderState : ChoiceState = uiState.personForm.getState("gender")
        genderState.change(genderName)
    }
    fun getTransformations() : List<MaskTransformation>{
        return listOf(
            MaskTransformation.CPF,
            MaskTransformation.DATE,
            MaskTransformation.PHONE
        )
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

    fun getPersonData(){
        val personForm = this.uiState.personForm
        val personDTO = personForm.getData(PersonData::class)
        savePerson(personDTO)
    }

    fun savePerson(person : PersonData){
        val (name,cpf,dateBirth, gender) = person
        user = user.copy(
            userName = name,
            cpf = cpf,
            birthDate = dateBirth,
            genderId = getGenderId(gender)
        )
        Log.i("UserApi Updated", user.toString())

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
data class PersonFormState(
    val personForm : FormState<TextFieldState> = FormState(
        fields = listOf(
            TextFieldState(
                name = "name",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo não pode estar vazio!"),
                    Validators.Max(100, "O nome pode ter até 100 caracteres.")
                )
            ),
            TextFieldState(
                name = "cpf",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.Max(14, "Cpf deve ter 14 caracteres : Somente número!"),
                    Validators.Custom("Este Cpf não é um cpf válido!") { validateCpf(it.toString()) }
                )
            ),
            TextFieldState(
                name = "phone",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.Phone("Deve ser um telefone válido!")
                )
            ),
            TextFieldState(
                name = "dateBirth",
                initial = "",
                validators = listOf(
                    Validators.Required("Este campo é obrigatório!"),
                    Validators.Custom("Deve ser uma data de nascimento válida!"){ validateData(it.toString()) }
                )
            ),
            ChoiceState(
                name = "gender",
                initial = "",
                validators = listOf(
                    Validators.Required("Escolha uma das opções!")
                )
            )
        )
    )
)

data class PersonData(
    val name: String,
    val cpf: String,
    val dateBirth : String,
    val gender : String
)
fun validateCpf(cpf: String) : Boolean{
    if(cpf.length > 14){
        return false
    }
    return true
}
fun validateData(date : String) : Boolean{
    return true
}


