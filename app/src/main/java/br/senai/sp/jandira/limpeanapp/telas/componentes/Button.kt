package br.senai.sp.jandira.limpeanapp.telas.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding

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
//import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
//import com.example.compose.blueButton
//import com.example.compose.closeBlack
import com.example.compose.md_theme_dark_background
import com.example.compose.md_theme_light_primary

@Composable
fun Button(
    name: String,
    containerColor: Color = md_theme_light_primary,
<<<<<<< HEAD
    onClick: () -> Unit,
=======
    action: () -> Unit,
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
    modifier: Modifier = Modifier) {
    androidx.compose.material3
        .Button(
            modifier = modifier,
<<<<<<< HEAD
            onClick = { onClick() },
=======
            onClick = { action() },
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
            shape = RoundedCornerShape(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
            ),
            border = BorderStroke(1.dp, md_theme_dark_background)
        ) {
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Button(name = "Próximo", onClick = { /*TODO*/ })
}