package br.senai.sp.jandira.limpeanapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme


@Composable
fun HomeContent(
    paddingValues : PaddingValues = PaddingValues(24.dp),
    content: @Composable () -> Unit = {}
){
    val topPadding = 28.dp
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .paddingFromBaseline(bottom = 40.dp)
        ,
        shape = RoundedCornerShape(
            topStart = topPadding,
            topEnd = topPadding
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ){
        content()
    }
}
@Preview
@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES, name = "NightMode")
@Composable
fun HomeContentPreview() {
    LimpeanAppTheme {
        HomeContent {

        }
    }
}