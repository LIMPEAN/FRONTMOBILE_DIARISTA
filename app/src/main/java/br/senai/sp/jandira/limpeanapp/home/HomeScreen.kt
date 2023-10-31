package br.senai.sp.jandira.limpeanapp.feature_diarist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.home.components.HomeNavBar
import br.senai.sp.jandira.limpeanapp.home.components.HomeNavGraph
import br.senai.sp.jandira.limpeanapp.home.components.HomeRoute
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.seed


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    title: String,
    description: String,
    content : @Composable () -> Unit
){
    val navController = rememberNavController()


    var topPadding = 54.dp
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = md_theme_light_primary,
        contentColor = Color.Black,
        topBar = {
            HomeAppBarTest(
                title = title,
                description = description
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(HomeRoute.FIND_CLEANING)},
                containerColor = seed,
                contentColor = Color(251,251,251)
            ) {
                Icon(Icons.Filled.Search, "Small floating action button.")
            }
        },
        bottomBar = {
            HomeNavBar(navController = navController)
        },

    ) {paddingValues ->
            Card(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                ,
                shape = RoundedCornerShape(
                    topStart = topPadding,
                    topEnd = topPadding
                )
            ){
                content()
            }
    }
}

@Composable
fun HomeAppBarTest(
    title : String,
    description : String,
) {
    Column(
        Modifier
            .padding(
                24.dp
            )
            .background(md_theme_light_primary)
    ) {
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = title,
            color = Color.White,
            fontFamily = poopins
        )
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = description,
            color = Color.White,
            fontFamily = poopins
        )
    }

}

@Preview()
@Composable
fun HomeScreenPrev() {
    LimpeanAppTheme {
        HomeScreen(title = "", description = ""){
            Text(text = "test")
        }
    }
}