package br.senai.sp.jandira.limpeanapp.core.data.mapper

import android.util.Log
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.mapper.toAddressRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist

fun Diarist.toRequestApi() : RegisterRequest{
    val dataFake = "2000-10-12"
    val averagePrice = ""

    val modelo =  RegisterRequest(
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
        address = this.address.toAddressRequest()
    )
    Log.i("MODELO", modelo.toString())
    return modelo
}