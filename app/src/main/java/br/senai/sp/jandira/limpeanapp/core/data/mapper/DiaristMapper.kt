package br.senai.sp.jandira.limpeanapp.core.data.mapper

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper.toAddressRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.dto.RegisterRequest
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import com.google.gson.Gson

fun Diarist.toRequestApi() : RegisterRequest {
    val dataFake = "2000-10-12"
    val averagePrice = "0.00" //FLOAT
//    val photoUser = "https://example.png"
    val defaultPhoto = "https://firebasestorage.googleapis.com/v0/b/tcc-limpean.appspot.com/o/imagens%2Fprofile-default.webp?alt=media&token=8a68000c-eb45-4948-9fae-f01a00a10d1e&_gl=1*1u1domm*_ga*MTAyMTA0OTYwOS4xNjk0NTU2NDQx*_ga_CW55HF8NVT*MTY5NjExNzIyOC4zLjEuMTY5NjExNzI4Ny4xLjAuMA.."

    val modelo =  RegisterRequest(
        typeUser = "diarist",
        email = this.email,
        password = this.password,
        nameUser = this.name,
        ddd = phones[0].ddd,
        phone = phones[0].number,
        birthDate = dataFake,
        photoUser = photo?: "",
        idGender = this.gender.id,
        cpf = this.cpf,
        biography = this.biography?: "Biografia ausente",
        averagePrice = averagePrice,
        address = this.address[0].toAddressRequest()
    )
    Log.i("MODELO", modelo.toString())
    val converter = Gson()
    Log.i("MODELO EM JSON",
        converter.toJson(modelo))
    return modelo
}



