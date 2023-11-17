package br.senai.sp.jandira.limpeanapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R

// Set of Material typography styles to start with


val Poppins = FontFamily(
    Font(R.font.poppins_light),
    Font(R.font.poopins_medium),
    Font(R.font.poppins_regular),
    Font(R.font.poopins_medium),
    Font(R.font.poppins_semibold),
    Font(R.font.poppins_thin)
)
val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    labelMedium = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF53575A),
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        color = Color(0xFF393939)
    ),

)