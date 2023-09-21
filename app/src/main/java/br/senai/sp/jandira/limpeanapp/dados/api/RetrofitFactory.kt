package br.senai.sp.jandira.limpeanapp.dados.api

import br.senai.sp.jandira.limpeanapp.dados.api.servicos.DiaristService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val BASE_URL = "http://localhost:8080/v1/limpean"

    fun gson() : Gson = GsonBuilder().create()

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

    fun getDiaristService() : DiaristService {
        return retrofitFactory.create(DiaristService::class.java)
    }
    fun getInstance() : Retrofit {
        return retrofitFactory
    }
}