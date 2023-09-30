package br.senai.sp.jandira.limpeanapp

import androidx.compose.ui.text.AnnotatedString
import br.senai.sp.jandira.limpeanapp.telas.cadastro.MaskTransformation
import org.junit.Assert.assertEquals
import org.junit.Test

class MaskTransformationTest {
    private val cpfMask = MaskTransformation("###.###.###-##")
    private val dataMask = MaskTransformation("##/##/####")
    private val phoneMask = MaskTransformation("## #####-####")

    fun filteringData(maskTransformation: MaskTransformation, string : String) : String{
        return maskTransformation.filter(AnnotatedString(string)).text.text
    }
    @Test
    fun test_If_Use_Mask_to_Cpf(){
       assertEquals(
           filteringData(cpfMask,"12345678910"),
           "123.456.789-10")
    }

    @Test
    fun test_If_Use_Mask_To_Data(){
        assertEquals(
            filteringData(dataMask,"24052023"),
            "24/05/2023"
        )
    }

    @Test
    fun test_If_Use_Mask_To_TelephoneFormat(){
        assertEquals(
            filteringData(phoneMask,"11956485456"),
            "11 95648-5456"
        )
    }
}