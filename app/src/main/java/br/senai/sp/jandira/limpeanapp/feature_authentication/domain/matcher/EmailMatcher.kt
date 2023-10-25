package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}