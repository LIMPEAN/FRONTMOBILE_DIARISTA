package br.senai.sp.jandira.limpeanapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabelError(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier
            .height(20.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
}