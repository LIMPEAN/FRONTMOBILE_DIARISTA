package br.senai.sp.jandira.limpeanapp.dados.api

import android.util.Log
import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val TYPE_CONECTION = "http"
    private val HOST = "10.107.145.233"
    private val PORT = 8080
    private val API_BASE = "v1/limpean"
//    private val BASE_URL = "$TYPE_CONECTION://$HOST:$PORT/$API_BASE/"
    private val BASE_URL = "http://$HOST:8080/v1/limpean/"

    fun gson() : Gson = GsonBuilder().create()

    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()


    fun getApiService() : ApiService {
        Log.e("TESTE_URL", BASE_URL)
        return retrofitFactory.create(ApiService::class.java)
    }
    fun getInstance() : Retrofit {
        return retrofitFactory
    }

    fun getUserService() : UserService {
        return retrofitFactory.create(UserService::class.java)
    }
}