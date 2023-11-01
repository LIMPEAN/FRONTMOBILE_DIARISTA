package br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.home.data.repository.cleanings
import com.example.compose.LimpeanAppTheme

@Composable
fun CleaningSchedules(
    modifier : Modifier = Modifier,
    cleanings : List<CleaningCardState>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CleaningSchedulesPreview() {
    LimpeanAppTheme {
        CleaningSchedules(cleanings = cleanings)

    }
}