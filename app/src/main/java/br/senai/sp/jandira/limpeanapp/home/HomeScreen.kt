package br.senai.sp.jandira.limpeanapp.feature_diarist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.navigation.HomeNavGraph
import br.senai.sp.jandira.limpeanapp.home.HomeViewModel
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningSchedules
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.cleanings
import br.senai.sp.jandira.limpeanapp.home.components.HomeNavBar
import br.senai.sp.jandira.limpeanapp.home.components.HomeRoute
import br.senai.sp.jandira.limpeanapp.home.components.HomeSection
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondary
import com.example.compose.seed


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel : HomeViewModel = hiltViewModel()
) {

    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    viewModel.findDiarist()
    HomeLayout(
        modifier,
        onCleaningService = { /*TODO*/ },
        topBar = {
            HomeTopBar(
                modifier,
                navController
            )
        },
        navController = navController
    ) {
        HomeNavGraph(
            navController = navController,
            paddingValues = it
        )
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeLayout(
    modifier : Modifier = Modifier.fillMaxWidth(),
    onCleaningService: ()-> Unit,
    topBar :  @Composable () -> Unit,
    navController : NavHostController,
    content : @Composable (PaddingValues) -> Unit,
){


    Scaffold(
        modifier = modifier,
        containerColor = md_theme_light_primary,
        contentColor = Color.Black,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCleaningService()},
                containerColor = seed,
                contentColor = Color(251,251,251)
            ) {
                Icon(Icons.Filled.Search, "Small floating action button.")
            }
        },
        bottomBar = {
            HomeNavBar(navController = navController) },

    ) {paddingValues ->
            content(paddingValues)
    }
}
@Composable
fun HomeContent(
    modifier : Modifier = Modifier,
    paddingValues : PaddingValues,
    content: @Composable () -> Unit
){
    var topPadding = 28.dp
    Card(
        modifier = modifier
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


@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    NavHost(navController, startDestination = HomeRoute.CLEANING){
        composable(HomeRoute.CLEANING){
            HomeTopBar(modifier = modifier,title = "Olá", description = "Felipe")
        }
        composable(HomeRoute.SCHEDULES) {
            HomeTopBar(modifier = modifier,title = "Fique de Olho!", description = "Agendamentos")
        }
        composable(HomeRoute.PROFILE) {
            HomeTopBar(modifier = modifier,title = "Perfil", description = "Teste")
        }
        composable(HomeRoute.NOTIFICATIONS){
            HomeTopBar(modifier = modifier,title = "Fique por dentro", description = "Notificacões")
        }
    }
    

}
@Composable
private fun HomeTopBar(
    modifier : Modifier = Modifier,
    title : String,
    description : String
){
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            )) {
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
    val navController = rememberNavController()
    LimpeanAppTheme {
        HomeLayout(
            onCleaningService = { /*TODO*/ },
            topBar = {
                HomeTopBar(
                    modifier = Modifier,
                    navController
                )
            },
            navController = navController
        ) {
            HomeNavGraph(navController = navController, paddingValues = it)
        }
    }
}