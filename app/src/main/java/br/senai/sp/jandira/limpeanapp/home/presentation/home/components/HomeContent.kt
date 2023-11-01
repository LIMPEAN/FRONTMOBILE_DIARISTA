package br.senai.sp.jandira.limpeanapp.home.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeContent(
    paddingValues : PaddingValues,
    content: @Composable () -> Unit
){
    val topPadding = 28.dp
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ,
        shape = RoundedCornerShape(
            topStart = topPadding,
            topEnd = topPadding
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        content()
    }
}