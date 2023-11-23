package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean



data class TokenResponse(
    val status: Number,
    val message: String?,
    val id: Number,
    val email : String,
    val token : String,
//    val name : String
) {

}
