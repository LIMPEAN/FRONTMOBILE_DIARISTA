package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.HomeInfoState


data class Client(
    val id : Number? = null,
    val name: String = "",
    val photo : String = "",
    val biography: String? = null
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
