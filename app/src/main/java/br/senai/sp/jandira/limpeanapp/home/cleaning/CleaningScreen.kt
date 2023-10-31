package br.senai.sp.jandira.limpeanapp.home.cleaning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Shower
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeScreen
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningCardActions
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.QuantityRoomsCategory
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.cleanings
import br.senai.sp.jandira.limpeanapp.home.components.HomeNavGraph
import br.senai.sp.jandira.limpeanapp.home.components.HomeRoute
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme

val quantityRooms = listOf(
    QuantityRoomsCategory(
        name = "Quarto",
        icon = Icons.Outlined.Bed,
        quantity = 3
    ),
    QuantityRoomsCategory(
        name = "Banheiro",
        icon = Icons.Outlined.Shower,
        quantity = 2
    ),
    QuantityRoomsCategory(
        name = "Sala",
        icon = Icons.Outlined.Chair,
        quantity = 3
    ),
    QuantityRoomsCategory(
        name = "Garagem",
        icon = Icons.Filled.Garage,
        quantity = null
    )
)

@Composable
fun CleaningScreen(
    cleanings : List<CleaningCardState>
) {

    Column(
        Modifier
            .padding(
                28.dp
            )
            .padding(bottom = 0.dp)
    ) {
        Text(
            text = "Suas faxinas",
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = poopins,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(){
           items(cleanings){
               CleaningCard(
                   nameClient = it.nameClient,
                   servicePrice = it.servicePrice,
                   local = it.local,
                   quantityRooms = it.quantityRooms,
                   actions = {
                        CleaningCardActions(
                            onStart = { /*TODO*/ },
                            onCancel = { TODO() }
                        )
                   },
                   onCleaningDetail = {}
               )
               Spacer(modifier = Modifier.height(12.dp))
           }
        }
    }
}

@Preview
@Composable
fun CleaningScreenPreview() {
    LimpeanAppTheme {
        val navController = rememberNavController()
        HomeScreen(title = "Seja Bem vinda", description = "Leslie") {
            HomeNavGraph(
                navController = navController,
                startDestination = HomeRoute.CLEANING
            )
        }
    }
}