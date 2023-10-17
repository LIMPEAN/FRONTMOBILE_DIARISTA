package br.senai.sp.jandira.limpeanapp.login.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.senai.sp.jandira.limpeanapp.login.data.api.AuthApi
import br.senai.sp.jandira.limpeanapp.login.data.repository.AuthRepositoryImpl
import br.senai.sp.jandira.limpeanapp.login.data.repository.TokenRepositoryImpl
import br.senai.sp.jandira.limpeanapp.login.domain.AuthRepository
import br.senai.sp.jandira.limpeanapp.login.domain.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")



@Module
@InstallIn(SingletonComponent::class)
object LoginModule {


    @Provides
    @Singleton
    fun provideAuthApi() : AuthApi {
        return Retrofit.Builder()
                .baseUrl("http://10.0.0.2:8080/v1/limpean")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }



    @Provides
    @Singleton
    fun provideTokenRepository(dataStore: DataStore<Preferences>) : TokenRepository{
        return TokenRepositoryImpl(dataStore)
    }
    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, tokenManager: TokenRepository): AuthRepository {
        return AuthRepositoryImpl(api = api, tokenManager = tokenManager)
    }



}