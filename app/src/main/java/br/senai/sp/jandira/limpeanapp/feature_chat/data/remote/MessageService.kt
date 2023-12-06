package com.plcoding.ktorandroidchat.data.remote

import com.plcoding.ktorandroidchat.domain.model.Message

interface MessageService {

    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "https://back-mongo-limpean-2023.azurewebsites.net"
    }

    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}