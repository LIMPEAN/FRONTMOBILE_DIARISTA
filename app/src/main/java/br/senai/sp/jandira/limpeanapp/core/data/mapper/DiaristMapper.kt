package br.senai.sp.jandira.limpeanapp.core.data.mapper

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist

fun Diarist.toRequestApi() : RegisterRequest{
    val dataFake = "2000-10-12"
    val averagePrice = ""
    return RegisterRequest(
        typeUser = "diarist",
        email = this.email,
        password = this.password,
        nameUser = this.name,
        ddd = this.ddd,
        phone = this.phone,
        birthDate = dataFake,
        photoUser = this.photo.toString(),
        idGender = this.gender.id,
        cpf = this.cpf,
        biography = null,
        averagePrice = averagePrice,
        address = this.address
    )
}