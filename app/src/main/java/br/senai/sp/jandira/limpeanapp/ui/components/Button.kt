package br.senai.sp.jandira.limpeanapp.ui.components

import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
//import com.example.compose.blueButton
//import com.example.compose.closeBlack
import com.example.compose.md_theme_dark_background
import com.example.compose.md_theme_light_primary

@Composable
fun Button(
    name: String,
    containerColor: Color = md_theme_light_primary,
    action: () -> Unit,
    height : Dp = 60.dp,
    width : Dp = 270.dp) {
    androidx.compose.material3
        .Button(
            modifier = Modifier.size(height = height, width = width),
            onClick = { action() },
            shape = RoundedCornerShape(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
            ),
            border = BorderStroke(1.dp, md_theme_dark_background)
        ) {
            Text(
                text = name,
                color = Color.Black,
                fontFamily = poopins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Button(name = "Próximo", action = { /*TODO*/ })
}