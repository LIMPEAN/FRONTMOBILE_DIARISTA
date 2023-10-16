package br.senai.sp.jandira.limpeanapp.authentication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.MainActivity
import com.google.common.truth.Truth.assertThat

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class AuthViewModelTest {


    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var viewModelTest : AuthViewModel
    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            viewModelTest = hiltViewModel()
        }
    }


    @Test
    fun testHiltViewModelWithDependencies(){
        val result = viewModelTest.helloFromRepository()
        assertThat(result).contains("Hello")
    }
}