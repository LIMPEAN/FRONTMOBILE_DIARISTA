package br.senai.sp.jandira.limpeanapp.ui.features.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.PhoneDto
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.AlertDialog
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.StarView
import com.example.compose.seed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val api : DiaristApi
) : ViewModel() {


    var resultado by mutableStateOf<DiaristDto?>(null)
        private set

    var telefone by mutableStateOf<PhoneDto?>(null)
        private set


    fun carregarDiarista() {
        runBlocking {
            val diaristDto = api.getDiarist().data


            var endereco = diaristDto?.address?.firstOrNull()
            var telefone = diaristDto?.phone?.firstOrNull()
            resultado = diaristDto
//            endereco = api.getDiarist().data
            Log.i("TESTE", resultado.toString())
        }
    }

    suspend fun apagarDiarista() {
        try {
            api.deleteDiarist()
            Log.i("ProfileViewModel", "Diarista excluído com sucesso")
        } catch (e: Exception) {
            // Trate a exceção de maneira apropriada
            Log.e("ProfileViewModel", "Erro ao excluir diarista: ${e.message}")
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun YourProfile(
    viewModel : ProfileViewModel = hiltViewModel<ProfileViewModel>(),
) {
    viewModel.carregarDiarista()


    val resultado = viewModel.resultado
    val api : DiaristApi


    var isDialogVisible by remember { mutableStateOf(false) }

    resultado?.let {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {

            },

            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .absoluteOffset(y = 20.dp)
                        .zIndex(Float.POSITIVE_INFINITY),
                    horizontalArrangement = Arrangement.Center,
                ) {
//                PhotoSemCamera(
//                    onAddPhoto = {
//                        singlePhotoPickerLauncher.launch(
//                            PickVisualMediaRequest(
//                                ActivityResultContracts
//                                    .PickVisualMedia
//                                    .ImageOnly
//                            )
//                        )
//                    },
//                    photo = {
//                        AsyncImage(
//                            model = results?.photoProfile ?: R.drawable.profile,
//                            contentDescription = "avatar do ${results?.name}",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Crop,
//                        )
//                    },
//                    isPhotoSelected = selectedPhotoUri == null
//                )
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(color = seed), verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        colors = CardDefaults.cardColors(Color.White),

                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    ) {
                        Spacer(modifier = Modifier.height(70.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(1) {
                                Text(
                                    text = resultado.name,
                                    fontSize = 30.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF19161D),
                                    letterSpacing = 0.2.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Spacer(modifier = Modifier.height(15.dp))
                                StarView(
                                    rating = (2.0)

                                )
                                Spacer(modifier = Modifier.height(30.dp))

                                Text(
                                    text = "Biografia:".uppercase(),
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.Black,
                                    textAlign = TextAlign.Left,
                                    letterSpacing = 0.2.sp
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                LazyColumn(modifier = Modifier.height(100.dp)) {
                                    item {
                                        Column(modifier = Modifier.fillMaxSize()) {
                                            Text(
                                                text = resultado.biography,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .wrapContentHeight(),
                                                fontSize = 16.sp,
                                                lineHeight = 20.sp,
                                                fontWeight = FontWeight(300),
                                                color = Color(25, 22, 29),
                                                textAlign = TextAlign.Justify,
                                                letterSpacing = 0.2.sp
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    text = "Dados pessoais:".uppercase(),
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.Black,
                                    textAlign = TextAlign.Left,
                                    letterSpacing = 0.2.sp
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.gender).toUpperCase(),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Black,
                                        textAlign = TextAlign.Left,
                                        letterSpacing = 0.2.sp
                                    )

                                    Text(
                                        text = resultado.gender,
                                        modifier = Modifier
                                            .padding(start = 5.dp),
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(300),
                                        color = Color(25, 22, 29),
                                    )
                                }

                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.locality).toUpperCase(),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Black,
                                        textAlign = TextAlign.Left,
                                        letterSpacing = 0.2.sp
                                    )

                                    Text(
                                        text = buildAnnotatedString {
                                            resultado?.address?.firstOrNull()?.let { addressDto ->
                                                append("${addressDto.city}, ${addressDto.state}")
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(start = 5.dp),
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(300),
                                        color = Color(25, 22, 29),
                                    )
                                }

                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.phone).toUpperCase(),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Black,
                                        textAlign = TextAlign.Left,
                                        letterSpacing = 0.2.sp
                                    )

                                    Text(
                                        text = buildAnnotatedString {
                                            resultado?.phone?.firstOrNull()?.let { phoneDto ->
                                                append("${phoneDto.ddd} ${phoneDto.numberPhone}")
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(start = 5.dp),
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(300),
                                        color = Color(25, 22, 29),
                                    )
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = seed),
                                        shape = RoundedCornerShape(size = 8.dp),
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.edit_profile_diarist),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = seed),
                                        shape = RoundedCornerShape(size = 8.dp),
                                        onClick = ({
                                            //chamando dialog alert para confirmaçao
                                            isDialogVisible = true

                                        })
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.delete_account),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }

                                    if (isDialogVisible) {
                                        AlertDialog(
                                            onDismissRequest = {
                                                isDialogVisible = false
                                            },
                                            onConfirmation = {
                                                viewModel.viewModelScope.launch {
                                                    try {
                                                        viewModel.apagarDiarista()
                                                        // Operação concluída com sucesso
                                                    } catch (e: Exception) {
                                                        // Trate a exceção de maneira apropriada
                                                        Log.e("Chamada ApagarDiarista", "Erro: ${e.message}")
                                                    }
                                                }



                                            },
                                            dialogTitle = "Deseja excluir sua conta?",
                                            dialogText = "Todos os demais cadastros vinculados a esta conta também serão removidos",
                                            icon = Icons.Default.Info
                                        )
                                    }
                                }


                                Spacer(modifier = Modifier.height(30.dp))

                                Text(
                                    text = stringResource(id = R.string.others),
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 12.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.Black,
                                    textAlign = TextAlign.Left,
                                    letterSpacing = 0.2.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.comments_disabled),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(22.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = stringResource(id = R.string.talk_to_support),
                                        fontSize = 12.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Black,
                                        textAlign = TextAlign.Left,
                                        letterSpacing = 0.2.sp
                                    )

                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Image(
                                        painter = painterResource(id = R.drawable.admin_panel_settings),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(22.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = stringResource(id = R.string.privacy_policy),
                                        fontSize = 12.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.Black,
                                        textAlign = TextAlign.Left,
                                        letterSpacing = 0.2.sp
                                    )
                                }
                            }
                        }

                    }
                }
            }
        )
    }

}


