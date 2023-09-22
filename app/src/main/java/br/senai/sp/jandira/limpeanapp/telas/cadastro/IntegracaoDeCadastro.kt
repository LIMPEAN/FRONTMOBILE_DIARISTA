package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.senai.sp.jandira.limpeanapp.dados.api.ApiService
import br.senai.sp.jandira.limpeanapp.dados.api.FakeApiService
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.modelos.CriarDiarista
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import br.senai.sp.jandira.limpeanapp.dados.modelos.Usuario
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeDiarista



class IntegracaoDeCadastro(
    private val repositorioDeDiarista: RepositorioDeDiarista
) : ViewModel(){

   var cadastroState by mutableStateOf(CadastroState())
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
        val diaristaFake = CriarDiarista(
            nomeTipoUsuario = "diarist",
            email = "felipe@gmail.co",
            senha = "1234567",
            nomeDaPessoa = "Felipe Florencio",
            fotoUri = "http:foto",
            telefone = "98456-4564",
            ddd = "11",
            dataDeNascimento = "12-03-2022",
            idDoGenero = 1,
            cpf ="56.5456",
            enderecoLocal = enderecoFake
        )
        repositorioDeDiarista.adicionarDiarista(diaristaFake)
        cadastroState = cadastroState.copy(status = "Deu Certo.")

    }

    companion object {
        val fazerIntegracaoComApi : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeDiarista = RepositorioDeDiarista(RetrofitFactory.getApiService())
                IntegracaoDeCadastro(repositorioDeDiarista)
            }
        }
        val fazerIntegracaoFake : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorioDeDiarista = RepositorioDeDiarista(apiService = FakeApiService())
                IntegracaoDeCadastro(repositorioDeDiarista)
            }
        }

    }


}
data class CadastroState(
    val status : String = "",
)