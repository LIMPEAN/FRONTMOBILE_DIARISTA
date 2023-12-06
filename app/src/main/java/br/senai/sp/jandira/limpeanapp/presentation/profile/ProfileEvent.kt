package br.senai.sp.jandira.limpeanapp.presentation.profile

import android.content.Context
import android.net.Uri
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender

sealed class ProfileDiaristEvent {

    data class OnPhotoSelected(val uri : Uri, val context : Context) : ProfileDiaristEvent()
    data class PhotoChanged(val nemValue: String) : ProfileDiaristEvent()

    data class NameChanged(val nemValue : String) : ProfileDiaristEvent()
    data class CpfChanged(val nemValue : String) : ProfileDiaristEvent()
    data class GenderChanged(val newValue : Gender) : ProfileDiaristEvent()
    data class EmailChanged(val nemValue : String) : ProfileDiaristEvent()
    data class BiographyChanged(val nemValue : String) : ProfileDiaristEvent()

    object OnEditMode : ProfileDiaristEvent()
    data class OnSaveUpdated(val context: Context) : ProfileDiaristEvent()
    object OnCancelUpdated : ProfileDiaristEvent()

    data class CepChanged(val nemValue : String) : ProfileDiaristEvent()
    data class NumberChanged(val nemValue : String) : ProfileDiaristEvent()
    data class AddressFetchedChanged(val address : Address) : ProfileDiaristEvent()





}