package br.senai.sp.jandira.limpeanapp.feature_authentication.login.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import br.senai.sp.jandira.limpeanapp.core.data.remote.AuthInterceptor
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.repository.AuthRepositoryImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.repository.SessionCacheImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.AuthRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.SessionCache
import br.senai.sp.jandira.limpeanapp.home.data.remote.DiaristApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


private const val USER_PREFERENCES = "user_preferences"
private const val HOST = "10.107.144.27"
@Module
@InstallIn(SingletonComponent::class)
object LoginModule {


    @Provides
    @Singleton
    fun providesAuthRepository(api : AuthApi, sessionCache: SessionCache) : AuthRepository{
        return AuthRepositoryImpl(api, sessionCache)
    }
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create()
    }
    @Provides
    @Singleton
    fun provideDiaristApi(retrofit: Retrofit) : DiaristApi {
        return retrofit.create()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://$HOST:8080/v1/limpean/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }



    @Provides
    @Singleton
    fun provideOkHttpClient(sessionCache: SessionCache) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(
                AuthInterceptor(sessionCache)
            )
            .build()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler (
                produceNewData =  { emptyPreferences() }
            ),
            migrations =  listOf(SharedPreferencesMigration(appContext, USER_PREFERENCES)) ,
            scope =  CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                appContext.preferencesDataStoreFile(USER_PREFERENCES)
            }
        )
    }

    @Provides
    @Singleton
    fun provideSessionCache(dataStore : DataStore<Preferences>): SessionCache{
        return SessionCacheImpl(dataStore)
    }
}