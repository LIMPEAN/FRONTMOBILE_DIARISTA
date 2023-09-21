package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.CriarDiarista
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import br.senai.sp.jandira.limpeanapp.dados.modelos.Usuario
import br.senai.sp.jandira.limpeanapp.dados.repositorios.RepositorioDeDiarista

class IntegracaoDeCadastro(
    private val repositorioDeDiarista: RepositorioDeDiarista
) : ViewModel(){

    private val _cadastroState by mutableStateOf(CadastroState())
    var cadastroState = _cadastroState
    fun cadastrarDiarista(usuario : Usuario?){

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
}
data class CadastroState(
    val status : String? = null,
)