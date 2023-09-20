package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.senai.sp.jandira.limpeanapp.dados.EnderecoLocal

@Composable
fun FormularioDeEndereco(
      salvarEndereco : (EnderecoLocal) -> Unit
) {

      var enderecoLocal by remember {
            mutableStateOf(EnderecoLocal())
      }
      Text(text = "Seu endereco ....")

      BotaoDeCadastro(nomeDaAcao = "Finalizar") {
            salvarEndereco(enderecoLocal)
      }
}

