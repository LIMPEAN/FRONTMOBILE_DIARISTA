package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.util.Log
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.dados.Usuario

class IntegracaoDeCadastro : ViewModel(){

    fun salvarPerfil(usuario : Usuario){
        Log.i("PERFIL-SALVO",usuario.toString() )
    }
}