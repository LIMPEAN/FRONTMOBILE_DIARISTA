package br.senai.sp.jandira.limpeanapp.home.cleaning

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Shower
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.QuantityRoomsCategory
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeContent
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningSchedules
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.cleanings
import br.senai.sp.jandira.limpeanapp.home.components.HomeSection
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




@Preview
@Composable
fun CleaningScreenPreview() {
    LimpeanAppTheme {
        val paddingValues = PaddingValues(20.dp)
        HomeContent(paddingValues = paddingValues) {
            HomeSection(title = "Suas faxinas") {
                CleaningSchedules(
                    modifier = Modifier.fillMaxWidth(),
                    cleanings = cleanings
                )
            }
        }
    }
}
