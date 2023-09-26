package br.senai.sp.jandira.limpeanapp.dados.api

import br.senai.sp.jandira.limpeanapp.dados.api.servicos.DiaristService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitFactory {



    private val TYPE_CONECTION = "http"
    private val HOST = "10.107.145.233"
    private val PORT = 8080
    private val API_BASE = "v1/limpean/"
    private val BASE_URL = "$TYPE_CONECTION://$HOST:$PORT/$API_BASE"
    fun gson() : Gson = GsonBuilder().create()

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

    fun getDiaristService() : DiaristService {
        return retrofitFactory.create(DiaristService::class.java)
    }
    fun getApiService() : ApiService {
        return retrofitFactory.create(ApiService::class.java)
    }
    fun getInstance() : Retrofit {
        return retrofitFactory
    }
}