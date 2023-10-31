package br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun TextComLinhasLogin(texto: String){
    Row (modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Card(
            colors = CardDefaults.cardColors(Color.LightGray),
            modifier = Modifier
                .width(85.dp)
                .height(0.8.dp)
        ){}

        Text(
            text = texto, color = md_theme_light_primary
        )

        Card(
            colors = CardDefaults.cardColors(Color.LightGray),
            modifier = Modifier
                .width(85.dp)
                .height(0.8.dp)
        ){}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextEmail(
    label: String,
    keyboardType: KeyboardType,
    state: String,
    onTyping : (String) -> Unit,
    modifier : Modifier = Modifier.fillMaxWidth()

    ) {
    OutlinedTextField(
        modifier = modifier,
        value = state,
        onValueChange = {
            onTyping(it)
        },
        label = {
            Text(
                text = label,
//                lineHeight = 16.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
//                fontWeight = FontWeight(400),
//                color = Color(0xFF53575A),
//                textAlign = TextAlign.Center
            )
        }
        ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(size = 8.dp)
    )
}

@Preview
@Composable
fun TextoComLinhasPreview() {
    LimpeanAppTheme {
        Column {

            TextComLinhasLogin(texto = "Teste")

        }

    }
}