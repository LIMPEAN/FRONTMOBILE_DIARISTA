package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.cleanings
import com.example.compose.LimpeanAppTheme

@Composable
fun ScheduleCleaning(
    modifier : Modifier = Modifier,
    cleanings : List<Cleaning>,
    onCleaningDetail : (Cleaning) -> Unit,
    onStart: (Cleaning) -> Unit,
    onCancel: (Cleaning) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(cleanings){
            val model = it.toCleaningCardState()
            CleaningCard(
                nameClient = model.nameClient,
                servicePrice = model.servicePrice,
                local = model.local,
                quantityRooms = model.quantityRooms,
                actions = {
                    CleaningCardActions(
                        onStart = { onStart(it) },
                        onCancel = { onCancel(it) }
                    )
                },
                onCleaningDetail = { onCleaningDetail(it) }
            )
        }
    }
}

