package br.senai.sp.jandira.limpeanapp.home.domain.models

import androidx.compose.ui.graphics.vector.ImageVector
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.HomeInfoState

data class Client(
    val name: String,
    val photo : String,
    val biography: String?
) {
    fun toAboutClientState(): AboutClientState {
        return AboutClientState(
            clientInfo = ClientInfoState(
                name = this.name,
                assentment = 0.0
            ),
            homeInfo = HomeInfoState(
                name = "Principal",
                typeHouse = "Casa"
            )
        )
    }
}
