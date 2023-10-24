package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.matcher.EmailMatcher
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.ErrorRepositoryException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.InvalidDiaristException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import javax.inject.Inject
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
class AddDiarist @Inject constructor(
    private val repository : DiaristRepository
) {

    @Throws(InvalidDiaristException::class)
    suspend operator fun invoke(diarist : Diarist){

        try {
            repository.insertDiarist(diarist)
        } catch (e: Exception){
            if(e.message != null){
                throw e
            }else{
                throw Exception("Erro com o Repositório.")
            }
        }

    }
}