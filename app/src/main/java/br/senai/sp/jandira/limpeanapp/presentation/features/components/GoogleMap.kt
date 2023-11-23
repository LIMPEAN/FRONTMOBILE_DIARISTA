package br.senai.sp.jandira.limpeanapp.presentation.features.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState



@Composable
fun GoogleMapContainer(
    modifier : Modifier = Modifier,
    local: LatLng,
    name: String,
    place: String,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(local, 13f)
    }
    val context = LocalContext.current
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ){
        Marker(
            state = MarkerState(position = local),
            title = name,
            snippet = place
        )
    }
}

@Preview
@Composable
fun GoogleMapPreview() {
    LimpeanAppTheme {
        Card(modifier = Modifier.size(200.dp)) {
            GoogleMapContainer(
                local = LatLng(
                    -23.595515,
                    -46.90905300000001
                ),
                name = "Rua da amizade, 374",
                place = "Casa Principal"
            )
        }
    }
}