package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.FakeApiService
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeDiarista
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor
import java.util.Date

class IntegracaoDeCadastro(
    private val repositorioDeDiarista: RepositorioDeDiarista,
) : ViewModel(){

   var cadastroState by mutableStateOf(CadastroState())
        private set
    fun cadastrarDiarista(diarista : DiaristaApi){
        val resultado = repositorioDeDiarista.adicionarDiarista(diarista)

        cadastroState = if(resultado){
            cadastroState.copy(status = "Top")
        } else {
            cadastroState.copy(status =  "deu errado")
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
               ddd = telefone?.ddd,
           )
       )

    }

    companion object {
        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeDiarista = RepositorioDeDiarista(RetrofitFactory.getApiService())
                IntegracaoDeCadastro(repositorioDeDiarista)
            }
        }
        fun fazerIntegracaoFake() : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeDiarista = RepositorioDeDiarista(apiService = FakeApiService())
                IntegracaoDeCadastro(repositorioDeDiarista)
            }
        }


    }


}
data class CadastroState(
    val status : String = "",
    val tipoDeUsuario : TipoDeUsuario? = null,
    val usuarioDaApi : DiaristaApi? = null
)