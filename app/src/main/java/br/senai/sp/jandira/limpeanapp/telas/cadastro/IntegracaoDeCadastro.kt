package br.senai.sp.jandira.limpeanapp.telas.cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.FakeApiService
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeUsuario
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import kotlinx.coroutines.launch


class IntegracaoDeCadastro(
    private val repositorioDeUsuario: RepositorioDeUsuario,
) : ViewModel(){

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

    companion object {
        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeUsuario = RepositorioDeUsuario(RetrofitFactory.getApiService())
                IntegracaoDeCadastro(repositorioDeUsuario)
            }
        }
        fun fazerIntegracaoFake() : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeUsuario = RepositorioDeUsuario(apiService = FakeApiService())
                IntegracaoDeCadastro(repositorioDeUsuario)
            }
        }

    }


}
data class CadastroState(
    val status : String = "",
    val tipoDeUsuario : TipoDeUsuario? = null,
    val usuarioDaApi : DiaristaApi? = null
)