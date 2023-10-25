package br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.mapper

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.AddressRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.repository.Estado
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address

fun Address.toAddressRequest() : AddressRequest{
    val sp = 26
    return AddressRequest(
        state = Estado.getStateIdBySigla(this.state)?: sp,
        city = this.city,
        cep = this.cep,
        publicPlace = this.street,
        houseNumber = this.number,
        complement = this.complement?: "",
        district = this.district
    )
}