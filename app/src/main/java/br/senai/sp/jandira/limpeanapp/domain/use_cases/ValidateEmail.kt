package br.senai.sp.jandira.limpeanapp.domain.use_cases

import android.util.Patterns

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "O email não pode estar vazio!"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Isso não é um email válido!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}