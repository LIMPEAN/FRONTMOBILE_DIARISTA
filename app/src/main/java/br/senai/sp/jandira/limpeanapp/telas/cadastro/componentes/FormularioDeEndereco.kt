package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import ViewModelCep
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.telas.componentes.Button
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_primaryContainer


@Composable
fun AddressForm(
      cepViewModel: ViewModelCep = viewModel(),
      validarCep: (String) -> Unit
) {


      var cep by remember { mutableStateOf("") }
      var endereco by remember { mutableStateOf("") }

      val uiState = cepViewModel.cepState




      var cepState by remember { mutableStateOf("") }
      var logradouroState by remember { mutableStateOf("") }
      var bairroState by remember { mutableStateOf("") }
      var estadoState by remember { mutableStateOf("") }
      var cidadeState by remember { mutableStateOf("") }
      var numeroState by remember { mutableStateOf("") }

      Scaffold(
            modifier = Modifier
                  .fillMaxSize()
                  .padding(24.dp)
            ,
            topBar = {
                  Box(
                        modifier = Modifier
                              .fillMaxWidth()
                              .fillMaxHeight(0.15f),
                        contentAlignment = Alignment.Center
                  ) {
                        Text(
                              text = "Adicione um Endereço",
                              textAlign = TextAlign.Center,
                              fontSize = 32.sp,
                              color = md_theme_light_primary
                        )
                  }

            },
            bottomBar = {
                  Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                  ) {
                        Button(
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                          start = 16.dp,
                                          top = 14.dp,
                                          end = 16.dp,
                                          bottom = 14.dp
                                    )

                              ,
                              name = "Cadastrar",

                              onClick = {}

                        )
                  }


            }
      ) {paddingValues ->
            Box(
                  modifier = Modifier.padding(paddingValues),
                  contentAlignment = Alignment.Center,
            ) {
                  Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                        OutlinedTextField(
                              label = {
                                    Text(text = "Cep")
                              },
                              value = cepState,
                              onValueChange = {
                                    if(it.length == 8){
                                          validarCep(it)
                                    }
                                    cepState = it
                              }
                        )
                        OutlinedTextField(
                              label = {
                                    Text(text = "Logradouro")
                              },
                              value = logradouroState,
                              onValueChange = {
                                    logradouroState = it
                              }
                        )
                        OutlinedTextField(
                              label = {
                                    Text(text = "Bairro")
                              },
                              value = bairroState,
                              onValueChange = {
                                    bairroState = it
                              }
                        )
                        OutlinedTextField(
                              label = {
                                    Text(text = "Estado")
                              },
                              value = estadoState,
                              onValueChange = {
                                    estadoState = it
                              }
                        )
                        OutlinedTextField(
                              label = {
                                    Text(text = "Cidade")
                              },
                              value = cidadeState,
                              onValueChange = {
                                    cidadeState = it
                              }
                        )
                        OutlinedTextField(
                              label = {
                                    Text(text = "Número")
                              },
                              value = numeroState,
                              onValueChange = {
                                    numeroState = it
                              }
                        )
                  }

            }

      }
}

@Preview(showSystemUi = true)
@Composable
fun AddressFormPreview() {
      LimpeanAppTheme {
            AddressForm(validarCep = {
                  Log.i("RecebendoCep", it)
            })
      }

}




