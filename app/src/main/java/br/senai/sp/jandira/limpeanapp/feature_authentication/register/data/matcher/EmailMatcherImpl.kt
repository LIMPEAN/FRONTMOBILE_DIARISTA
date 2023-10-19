package br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.matcher

import android.util.Patterns
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}