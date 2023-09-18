package br.senai.sp.jandira.limpeanapp.regras.use_cases

import android.util.Patterns

class ValidatePhone {
    fun execute(number: String) : ValidationResult{
        if(!Patterns.PHONE.equals(number)){
            return ValidationResult(
             successful = false, errorMessage = "O número digitado não é um número de telefone!"
            )
        }
        return ValidationResult(true)

    }
}