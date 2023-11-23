package br.senai.sp.jandira.limpeanapp.core.data.remote.dto


data class BaseDto<T> (
    val status: Number,
    val message : String,
    val data : T
)
data class BaseResponseDto(
    val status: Number,
    val message: String
)