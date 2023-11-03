package br.senai.sp.jandira.limpeanapp.feature_authentication.di

import br.senai.sp.jandira.limpeanapp.core.data.repository.impl.DiaristRepositoryImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.ViaCepApi
import br.senai.sp.jandira.limpeanapp.core.domain.repository.DiaristRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RegisterModule{


    @Provides
    @Singleton
    fun provideGsonBuilder() : Gson{
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    fun provideViaCepApi(
        gson : Gson
    ): ViaCepApi {
        return Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideDiaristRepository(api: AuthApi) : DiaristRepository {
        return DiaristRepositoryImpl(api)
    }
}