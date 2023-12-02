package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.assentment

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto

data class ResponseAssentmentDto(
    val status: Number,
    val message: BaseResponseDto
)