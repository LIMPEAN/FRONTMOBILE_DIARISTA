package br.senai.sp.jandira.limpeanapp.regras

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


class MaskTransformation(val mask: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val maskedText = maskText(text.text)
        return TransformedText(AnnotatedString(maskedText), OffsetMapping.Identity)
    }

    private fun maskText(input: String
    ): String {
        val cleaned = input.filter { it.isDigit() }
        val masked = StringBuilder()
        var index = 0

        for (char in mask) {
            if (index >= cleaned.length) break

            if (char == '#') {
                masked.append(cleaned[index])
                index++
            } else {
                masked.append(char)
            }
        }

        return masked.toString()
    }
    companion object{
        val CPF = MaskTransformation("###.###.###-##")
        val DATE = MaskTransformation("##/##/####")
        val PHONE = MaskTransformation("## #####-####")
    }
}