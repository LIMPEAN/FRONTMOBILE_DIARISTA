package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

import android.net.Uri
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.matcher.EmailMatcherImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.matcher.EmailMatcher
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.InvalidDiaristException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest



import org.junit.Before
import org.junit.Test
import java.time.LocalDate


val diaristTest = Diarist(
    id = 2,
    name = "Giovani",
    cpf = "123456789089",
    phone ="11 91234-5678",
    email = "ana@gmail.com",
    password = "123456",
    dateOfBirth = LocalDate.of(2000,10,4),
    address = addressFake,
    photo = Uri.EMPTY,
    gender = Gender.MASCULINO,
    biography = null
)
class AddDiaristTest{

    lateinit var addDiaristUseCase : AddDiarist
    private lateinit var repository: DiaristRepository
    private lateinit var emailMatcher : EmailMatcher
    private lateinit var diarist : Diarist
    @Before
    fun setup(){
        emailMatcher = EmailMatcherImpl()
        repository = DiaristFakeRepository()
        addDiaristUseCase = AddDiarist(repository ,emailMatcher)
        diarist = diaristTest


    }


    @Test()
    fun `não deve ser permitido criar duas diaristas`() = runBlocking{

        addDiaristUseCase = AddDiarist(repository, emailMatcher)

        try {
            addDiaristUseCase(diarist)
        } catch (e: InvalidDiaristException.UserAlreadyExists) {
            assertThat("User already exists").isEqualTo(e.message)
        }
    }






}