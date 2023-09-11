package br.senai.sp.jandira.limpeanapp.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.md_theme_light_primary

@Composable
fun SectionButton(
    name : String,
) {
    Button(
        modifier = Modifier
            .width(330.dp)
            .height(61.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp)),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.White
        )
    ) {
        Text(text = name,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = poopins,
                fontWeight = FontWeight(400),
                color = md_theme_light_primary,
                letterSpacing = 0.2.sp,
            )
        )
    }
}