package br.senai.sp.jandira.limpeanapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    helperText: String = "",
) {
    Column(
        modifier = modifier
            .width(368.dp)
            .height(68.dp)
            .then(Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            }),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF53575A),
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xFFEBEBEB), shape = RoundedCornerShape(40.dp))
                .height(48.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(40.dp))
                .padding(start = 24.dp, top = 6.dp, end = 24.dp, bottom = 6.dp)
        )

        if (helperText.isNotBlank()) {
            Text(
                text = helperText,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputTextPreview() {
    var state by remember {
        mutableStateOf("")
    }
    LimpeanAppTheme {
        Column {
            InputText(
                value = state,
                onValueChange = { newValue -> state = newValue },
                label = "NOME",
                helperText = "Felipe De Oliveira Florencio"
            )
        }
    }
}