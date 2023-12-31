package br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper

import android.util.Log
import java.util.Locale

enum class StateMapper(val value: Int) {
    AC(1),
    AL(2),
    AM(3),
    AP(4),
    BA(5),
    CE(6),
    DF(7),
    ES(8),
    GO(9),
    MA(10),
    MG(11),
    MS(12),
    MT(13),
    PA(14),
    PB(15),
    PE(16),
    PI(17),
    PR(18),
    RJ(19),
    RN(20),
    RO(21),
    RR(22),
    RS(23),
    SC(24),
    SE(25),
    SP(26),
    TO(27);

    companion object {
        fun getStateIdBySigla(sigla: String): Int? {
            val upperCaseSigla = sigla.uppercase(Locale.getDefault())
            for (estado in values()) {
                if (estado.name == upperCaseSigla) {
                    Log.i("ESTADO", estado.value.toString())
                    return estado.value
                }
            }
            return null
        }
    }
}