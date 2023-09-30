package br.senai.sp.jandira.limpeanapp.dados.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val conection = "http"
    private const val host = "10.107.144.22"
    private const val baseApi = "/v1/limpean"

    private const val baseUrl =  "$conection://$host:8080$baseApi}"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}