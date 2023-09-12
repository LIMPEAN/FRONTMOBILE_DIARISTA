package br.senai.sp.jandira.limpeanapp.api

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    var status: Number,

    @SerializedName("message")
    var message: String
)