package br.senai.sp.jandira.limpeanapp.telas.cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.FakeApiService
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
    private val tipoDeUsuario : TipoDeUsuario
) : ViewModel(){

   var cadastroState by mutableStateOf(CadastroState(tipoDeUsuario = tipoDeUsuario))
        private set
    fun cadastrarDiarista(){

        val enderecoFake = Endereco(
            bairro = "",
            cep = "",
            cidade = "",
            complemento = "",
            numeroDaCasa = 100,
            numeroDoEstado = 1,
            rua = "")
        val diaristaFake = DiaristaApi(
            nomeTipoUsuario = "diarist",
            email = "felipe@gmail.co",
            senha = "1234567",
            nomeDaPessoa = "Felipe Florencio",
            fotoUri = "http:foto",
            telefone = "98456-4564",
            ddd = 11,
            dataDeNascimento = LocalDate.of(2000,10,3),
            idDoGenero = 1,
            cpf ="56.5456",
            enderecoLocal = enderecoFake
        )
        repositorioDeDiarista.adicionarDiarista(diaristaFake)
        cadastroState = cadastroState.copy(status = "Deu Certo.")

    }

    fun alterarDadosDePessoa(novaPessoa : Pessoa){
        val (nome,dataDeNascimento,genero,cpf,telefone) = novaPessoa
        cadastroState = cadastroState.copy(
           usuarioDaApi = DiaristaApi(
               nomeTipoUsuario = nome,
               dataDeNascimento = dataDeNascimento,
               idDoGenero = genero?.id,
               cpf = cpf,
               telefone = telefone?.numero,
               ddd = telefone?.ddd,
           )
       )

    }

    companion object {
//        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val repositorioDeDiarista = RepositorioDeDiarista(RetrofitFactory.getApiService())
//                IntegracaoDeCadastro(repositorioDeDiarista)
//            }
//        }
        fun fazerIntegracaoFake(tipoUsuario : TipoDeUsuario) : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeDiarista = RepositorioDeDiarista(apiService = FakeApiService())
                IntegracaoDeCadastro(repositorioDeDiarista, tipoUsuario)
            }
        }

    }


}
data class CadastroState(
    val status : String = "",
    val tipoDeUsuario : TipoDeUsuario,
    val usuarioDaApi : DiaristaApi? = null
)