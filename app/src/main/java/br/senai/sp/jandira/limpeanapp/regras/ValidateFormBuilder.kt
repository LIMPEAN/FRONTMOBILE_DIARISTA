package br.senai.sp.jandira.limpeanapp.regras

import com.dsc.form_builder.Validators

object ValidateFormBuilder {

    fun PersonForm(){

    }
    fun AdressForm(){

    }

    fun validateEmail() : List<Validators>{
        return listOf(
            isRequired(),
            Validators.Email("O email digitado não é valido!")
        )
    }
    fun isRequired(): Validators{
        return Validators.Required("Este campo é obrigatório!")
    }
    fun validatePassword(): List<Validators>{
        return listOf(
            isRequired()
        )
    }


}