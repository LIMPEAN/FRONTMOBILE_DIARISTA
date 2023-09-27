package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.BaseResponse
import br.senai.sp.jandira.limpeanapp.dados.api.DiaristsModel
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.api.apiService
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeUsuario
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import kotlin.math.log


class IntegracaoDeCadastro(
    private val repositorioDeUsuario: RepositorioDeUsuario,
) : ViewModel(){

    var listaDeUsuarios by mutableStateOf(listOf<DiaristsModel>())
        private set
    var cadastroState by mutableStateOf(CadastroState())
        private set
    fun cadastrarDiarista(diarista : DiaristaApi){
        viewModelScope.launch {
            repositorioDeUsuario.adicionarUsuario(diarista)
            }
        }



    fun alterarTipoDeUsuario(tipoDeUsuario : TipoDeUsuario){
        cadastroState = cadastroState.copy(tipoDeUsuario = tipoDeUsuario)
    }
    fun alterarDadosDePessoa(novaPessoa : Pessoa){
        val (nome,dataDeNascimento,genero,cpf,telefone) = novaPessoa
        cadastroState = cadastroState.copy(
           usuarioDaApi = DiaristaApi(
               nomeTipoUsuario = nome,
               idDoGenero = genero?.id,
               cpf = cpf,
               telefone = telefone?.numero,
               ddd = telefone?.ddd.toString(),
           )
       )

    }

    fun createUser(usuario : DiaristaApi){
        viewModelScope.launch {
//            val serializado = Gson().toJson(usuario.enderecoLocal, Endereco::class.java)
            val enderecoFinal = JsonObject().apply {
                addProperty("state", usuario.enderecoLocal!!.numeroDoEstado)
                addProperty("city", "${usuario.enderecoLocal!!.cidade}")
                addProperty("cep", "${usuario.enderecoLocal!!.cep}")
                addProperty("publicPlace", "${usuario.enderecoLocal!!.rua}")
                addProperty("complement", "${usuario.enderecoLocal!!.complemento}")
                addProperty("district", "${usuario.enderecoLocal!!.bairro}")
                addProperty("houseNumber", "${usuario.enderecoLocal!!.numeroDaCasa}")
            }
            val body = JsonObject().apply {
                addProperty("typeUser", "${usuario.nomeTipoUsuario}")
                addProperty("email", "${usuario.email}")
                addProperty("password", "${usuario.senha}")
                addProperty("nameUser", "${usuario.nomeDaPessoa}")
                addProperty("photoUser", "${usuario.fotoUri}")
                addProperty("phone", "${usuario.telefone}")
                addProperty("ddd", "${usuario.ddd}")
                addProperty("birthDate", "${usuario.dataDeNascimento}")
                addProperty("idGender", usuario.idDoGenero)
                addProperty("cpf", "${usuario.cpf}")
                addProperty("biography", "${usuario.biografia}")
                addProperty("averagePrice", "${usuario.precoMedio}")
            }
            body.add("address",enderecoFinal)

            Log.i("DS3M", "$body")
//            val teste = Gson().toJson(usuario,DiaristaApi::class.java)
//            Log.i("GSON", teste)
            val result = apiService().createUser(body)


            if (result.isSuccessful) {
                Log.e("CREATEDATA", "${result.body()}")
                Log.i("STATUS-RESULT-OBJECT", result.message())
                Log.i("STATUS-RESULT-OBJECT", "${result.code()}")

            } else {
                Log.e("CREATEDATA", "${result.message()}")
                Log.i("STATUS-RESULT-OBJECT", result.message())
                Log.i("STATUS-RESULT-OBJECT", "${result.code()}")
            }




        }
    }

    fun mostreAsDiarista(){
        viewModelScope.launch {
           val response =  apiService().listAllDiarists()
            if(response.isSuccessful){
                Log.i("Vaidormi", response.toString())
                Log.i("VaidormiBody", response.body().toString())
                cadastroState = cadastroState.copy(diaristas = response.body()?.diarists)
            }
        }


    }
    companion object {
        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeUsuario = RepositorioDeUsuario(RetrofitFactory.getApiService())
                IntegracaoDeCadastro(repositorioDeUsuario)
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
    val usuarioDaApi : DiaristaApi? = null,
    val diaristas : List<DiaristsModel>? = null,
)