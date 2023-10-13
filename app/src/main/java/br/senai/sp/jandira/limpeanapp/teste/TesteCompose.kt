package br.senai.sp.jandira.limpeanapp.teste


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.LimpeanAppTheme

@Composable
fun Oi(
    meuModelo : Definir30 = viewModel()
) {
    var estado = remember {
        meuModelo.resultado
    }

    Column {
        Text(text = "Resultado : $estado")
        Text(text = "Oi")
        Button(onClick = { meuModelo.definir30() },
            content = { Text(text = "Definir 30")})
    }

}




@Preview(showSystemUi = true)
@Composable
fun OiPreview() {


    val meuModelo = Definir30()
    LimpeanAppTheme { ""
        Oi()
    }

}

class Definir30(){

    var resultado by mutableStateOf("ALOU")

    fun definir30(){
        resultado = "30"
    }


    fun noEvento(evento : EventoDefinir30){

        when (evento){
            is EventoDefinir30.ValorMudou -> {
                definir30()
                Log.i("Resultado", resultado)
            }
        }
    }

}
sealed class EventoDefinir30 {
    object ValorMudou : EventoDefinir30()
}