package br.senai.sp.jandira.limpeanapp

import android.util.Log
import br.senai.sp.jandira.limpeanapp.login.UserType
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DefineUserType {
    @Test
    fun userType_User() {
        val diarist = UserType.diarist
        assertEquals(diarist, UserType.diarist)
    }
}