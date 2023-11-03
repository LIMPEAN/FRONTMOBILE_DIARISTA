package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.cleanings
import com.example.compose.LimpeanAppTheme

@Composable
fun CleaningSchedules(
    modifier : Modifier = Modifier,
    cleanings : List<CleaningCardState>,
    onCleaningDetail : (Number) -> Unit,
    onStart: (Number) -> Unit,
    onCancel: (Number) -> Unit
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
                        onStart = { onStart(it.id) },
                        onCancel = { onCancel(it.id) }
                    )
                },
                onCleaningDetail = { onCleaningDetail(it.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CleaningSchedulesPreview() {
    LimpeanAppTheme {
        CleaningSchedules(
            cleanings = cleanings,
            onCancel = {},
            onStart = {},
            onCleaningDetail = {}
        )

    }
}