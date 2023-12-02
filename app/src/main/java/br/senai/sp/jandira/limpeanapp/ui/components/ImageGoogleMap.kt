package br.senai.sp.jandira.limpeanapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.GetLatLgnForMapViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.type.LatLng

@Composable
fun ImageGoogleMap(
    address: Address,
    viewModel : GetLatLgnForMapViewModel = hiltViewModel()
) {
    viewModel.getLatLgn(address)

    val latLng = viewModel.state.latLgn
    var url by remember {
        mutableStateOf("")
    }
    LaunchedEffect(viewModel.state){
        viewModel.state.latLgn?.let {
            url = "https://maps.googleapis.com/maps/api/streetview?size=1000x1000&location=${it.latitude},${it.longitude}&key=AIzaSyCLXaX9MfiGsaZOonUpXtsfC6CN8AaRZcE"
            Log.i("URL", url)
        }
    }

    // Log da URL para debug

    val painter = rememberAsyncImagePainter(
        model = url
    )
    // Componente de interface do usuário
    Box(
        modifier = Modifier
            .heightIn(min = 20.dp, max = 100.dp)
            .fillMaxWidth()
    ) {
        // Exibir a imagem do mapa
        Image(
            painter = painter,
            contentDescription = "Image map",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray) // Cor de fundo enquanto a imagem está carregando
        )

        // Exibir o indicador de progresso durante o carregamento da imagem
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp) // Tamanho do indicador de progresso
                    .align(Alignment.Center) // Alinhar ao centro da caixa
            )
        }
    }
}