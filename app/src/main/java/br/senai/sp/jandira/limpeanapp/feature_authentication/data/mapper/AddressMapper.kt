package br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.dto.AddressRequest
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address

fun Address.toAddressRequest() : AddressRequest {
    val sp = 26
    return AddressRequest(
        state = StateMapper.getStateIdBySigla(this.state)?: sp,
        city = this.city,
        cep = this.cep,
        publicPlace = this.street,
        houseNumber = this.number,
        complement = this.complement?: "",
        district = this.district
    )
}