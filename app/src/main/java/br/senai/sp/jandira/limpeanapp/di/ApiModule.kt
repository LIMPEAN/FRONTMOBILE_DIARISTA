package br.senai.sp.jandira.limpeanapp.di

import br.senai.sp.jandira.limpeanapp.authentication.AuthRepository
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginViewModel
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return RetrofitFactory.getUserService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(userService: UserService) : AuthRepository{
        return AuthRepository(userService)
    }
    @Provides
    @Singleton
    fun provideLoginViewModel(authRepository: AuthRepository): LoginViewModel{
        return LoginViewModel(authRepository)
    }
}