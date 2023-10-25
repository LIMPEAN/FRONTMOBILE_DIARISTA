package br.senai.sp.jandira.limpeanapp.core.data.mapper

import android.util.Log
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.mapper.toAddressRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import com.google.gson.Gson

fun Diarist.toRequestApi() : RegisterRequest{
    val dataFake = "2000-10-12"
    val averagePrice = "0.00" //FLOAT
    val photoUser = "https://example.png"

    val modelo =  RegisterRequest(
        typeUser = "diarist",
        email = this.email,
        password = this.password,
        nameUser = this.name,
        ddd = this.ddd,
        phone = this.phone,
        birthDate = dataFake,
        photoUser = photoUser,
        idGender = this.gender.id,
        cpf = this.cpf,
        biography = this.biography?: "Biografia ausente",
        averagePrice = averagePrice,
        address = this.address.toAddressRequest()
    )
    Log.i("MODELO", modelo.toString())
    val converter = Gson()
    Log.i("MODELO EM JSON",
        converter.toJson(modelo))
    return modelo
}