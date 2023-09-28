package br.senai.sp.jandira.limpeanapp.dados.viaCep

import br.senai.sp.jandira.limpeanapp.dados.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactoryCep {

    private val BASE_URL = "https://viacep.com.br/ws/"
    fun gson() : Gson = GsonBuilder().create()

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

    fun getCepService() : ApiServiceCep {
        return retrofitFactory.create(ApiServiceCep::class.java)
    }


}


