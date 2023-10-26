package br.senai.sp.jandira.limpeanapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.repository.SessionCacheImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.SessionCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



}