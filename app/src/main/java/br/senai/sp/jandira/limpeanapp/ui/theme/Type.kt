package br.senai.sp.jandira.limpeanapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poopins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
)