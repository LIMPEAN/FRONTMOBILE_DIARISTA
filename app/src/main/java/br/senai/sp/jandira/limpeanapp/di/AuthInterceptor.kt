package br.senai.sp.jandira.limpeanapp.di

import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionCache : SessionCache,
) : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val activeSession = runBlocking {
            sessionCache.getActiveSession()
        }
        activeSession?.let {
            requestBuilder.addHeader("x-api-key", it.token)
        }

        return chain.proceed(requestBuilder.build())
    }
}