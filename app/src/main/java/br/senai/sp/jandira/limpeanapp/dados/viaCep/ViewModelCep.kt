import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.dados.viaCep.BaseResponseCep
import br.senai.sp.jandira.limpeanapp.dados.viaCep.RetrofitFactoryCep
import br.senai.sp.jandira.limpeanapp.dados.viaCep.RetrofitHelperCep
import kotlinx.coroutines.launch

class ViewModelCep: ViewModel() {
    // Função para buscar informações do CEP

    var cepState by  mutableStateOf(BaseResponseCep())
        private set
    fun fetchCep(cep: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitFactoryCep.getCepService().getAdressByCep(cep)
                val cepDaApi = response.body()
                cepState = cepState.copy(
                    cep = cepDaApi?.cep,
                    logradouro = cepDaApi?.logradouro,
                    complemento = cepDaApi?.complemento,
                    bairro = cepDaApi?.bairro,
                    localidade = cepDaApi?.localidade,
                    uf = cepDaApi?.uf,
                    ddd = cepDaApi?.ddd
                )
                Log.i("VIEWMODEL", "${response.body()}")
                // Faça algo com os dados do CEP (por exemplo, atualize um LiveData)
            } catch (e: Exception) {
                // Trate erros aqui
            }
        }
    }
}
