package br.senai.sp.jandira.limpeanapp.regras.use_cases

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "As senhas não estão iguais!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}