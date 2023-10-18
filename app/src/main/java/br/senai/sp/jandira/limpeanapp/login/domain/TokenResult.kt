package br.senai.sp.jandira.limpeanapp.login.domain

sealed class TokenResult<T>(val data: T? = null) {
    class SuccessfulSaveToken<T>(): TokenResult<T>()
    class UnknownError<T>: TokenResult<T>()
    class FirstTokenFounded<T>(data: T? = null) : TokenResult<T>()
}
