package br.senai.sp.jandira.limpeanapp.dados.viaCep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelperCep {

    private const val BASE_URL = "https://viacep.com.br/ws/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}