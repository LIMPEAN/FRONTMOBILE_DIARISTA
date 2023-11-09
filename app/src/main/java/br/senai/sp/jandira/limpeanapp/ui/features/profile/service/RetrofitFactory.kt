package br.senai.sp.jandira.limpeanapp.ui.features.profile.service

import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL_BASE = "http://10.107.144.14:8080/v1/limpean/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getDiaristService(): DiaristApi {
        return retrofitFactory.create(DiaristApi::class.java)
    }
}
