package br.senai.sp.jandira.limpeanapp.domain.use_cases

class ValidateName{

    fun execute(name: String): ValidationResult {
        if(name.isNotBlank()){
            ValidationResult(successful = false, "O nome não pode estar vazio")
        }
        return ValidationResult(successful = true)
    }
}