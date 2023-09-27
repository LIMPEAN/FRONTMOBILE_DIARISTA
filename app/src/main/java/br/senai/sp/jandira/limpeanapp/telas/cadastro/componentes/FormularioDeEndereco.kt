package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import com.example.compose.LimpeanAppTheme

@Composable
fun FormularioDeEndereco(
      salvarEndereco : (Endereco) -> Unit
) {

      var endereco by remember {
            mutableStateOf(Endereco())
      }
      Text(text = "Seu endereco ....")
      
      BotaoDeCadastro(nomeDaAcao = "Finalizar") {
            salvarEndereco(endereco)
      }
}

@Preview(showSystemUi = true)
@Composable
fun FormularioDeEnderecoPreview() {
      FormularioDeEndereco {
      }
}

