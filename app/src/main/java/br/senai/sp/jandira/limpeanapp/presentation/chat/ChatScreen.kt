package br.senai.sp.jandira.limpeanapp.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.HeaderText


data class Contact(
    val name : String,
    val photo : String,

)

@Composable
fun ChatScreen(
    contacts : List<Contact> = emptyList()
) {
    Column {

        HeaderText(title = "Chat")
        LazyColumn(){
            items(contacts){

            }
        }
    }
}