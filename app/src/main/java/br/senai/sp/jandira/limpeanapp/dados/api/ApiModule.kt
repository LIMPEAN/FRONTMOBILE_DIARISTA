package br.senai.sp.jandira.limpeanapp.dados.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

fun client() =
    OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

fun retrofit() : Retrofit = RetrofitFactory.getInstance()

fun apiService() = retrofit().create(ApiService::class.java)