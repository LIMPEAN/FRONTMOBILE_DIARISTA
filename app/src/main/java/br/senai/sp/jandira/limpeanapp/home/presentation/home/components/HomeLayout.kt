package br.senai.sp.jandira.limpeanapp.home.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.compose.md_theme_light_primary

@Composable
fun HomeLayout(
    modifier : Modifier = Modifier,
    topBar : @Composable ()-> Unit,
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        modifier = modifier,
        containerColor = md_theme_light_primary,
        contentColor = Color.Black,
        topBar = topBar,
    ) {paddingValues ->
        content(paddingValues)
    }
}