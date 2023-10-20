package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.matcher.EmailMatcher
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.InvalidDiaristException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import kotlin.jvm.Throws


/*
* Deve ser possível cadastrar com os seguintes critérios:
*
* Cadastro Único dos seguintes valores:
    * Email deve ser único
    * Telefone deve ser único
* O email deve estar no formato de email
*  - A data deve ter um padrão de data. (2000/12/01)
* */
class AddDiarist(
    private val repository : DiaristRepository,
    private val emailMatcher: EmailMatcher
) {

    @Throws(InvalidDiaristException::class)
    suspend operator fun invoke(diarist : Diarist){
//
//        if(!Diarist.genders.contains(diarist.gender)){
//            throw InvalidDiaristException("O gênero deve ser uma opção da lista.")
//        }
//
//        if(diarist.email =="Felipe"){
//            throw InvalidDiaristException("Teste")
//        }

//        val validEmail = emailMatcher.isValid(diarist.email)
//        if (!validEmail){
//            throw InvalidDiaristException("Email inválido!")
//        }
//        repository.getDiaristByPhoneAndEmail(
//            phone = diarist.phone,
//            email = diarist.email
//        ) ?: throw InvalidDiaristException("Esta diarista já foi cadastrada!")
        val teste = ""
        throw InvalidDiaristException("Teste")
    }
}