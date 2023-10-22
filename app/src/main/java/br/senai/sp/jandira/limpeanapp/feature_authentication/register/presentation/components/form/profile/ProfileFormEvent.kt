package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.RegisterEvent

sealed class ProfileFormEvent{

    data class NameChanged(val value: String) : ProfileFormEvent()
    data class CpfChanged(val value: String) : ProfileFormEvent()

    data class DDDChanged(val value: String) : ProfileFormEvent()
    data class PhoneChanged(val value: String) : ProfileFormEvent()
    data class EmailChanged(val value: String) : ProfileFormEvent()
    data class PasswordChanged(val value: String) : ProfileFormEvent()
}
