package br.senai.sp.jandira.limpeanapp

import br.senai.sp.jandira.limpeanapp.regras.Diarist
import br.senai.sp.jandira.limpeanapp.regras.Address
import br.senai.sp.jandira.limpeanapp.regras.Gender
import br.senai.sp.jandira.limpeanapp.regras.Person
import br.senai.sp.jandira.limpeanapp.regras.Phone
import br.senai.sp.jandira.limpeanapp.regras.User
import org.junit.Test

import org.junit.Assert.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//class DefineUserType {
//    @Test
//    fun userType_User() {
//        val diarist = UserType.diarist
//        assertEquals(diarist, UserType.diarist)
//    }
//}

class TestCreationDiarist {
    @Test
    fun test_createDiarist() {
        val person = Person(
            name = "felipe",
            birthDate = Date(),
            gender = Gender(id = 1, "Masculino", "M"),
            cpf = "45615815"
        )
        val user = User(
            phone = Phone(
                45645, 11
            ),
            email = "felipe@gmail.com",
            password = "rwearw",
            personData = person,
            biography = null
        )

        val diarist = Diarist(1,user, Address(1,"cotia",1546451,"sei lá","ksjdkfj"))

        assertEquals(diarist.userData, user )
        assertEquals(diarist.userData.personData, person )
    }
}