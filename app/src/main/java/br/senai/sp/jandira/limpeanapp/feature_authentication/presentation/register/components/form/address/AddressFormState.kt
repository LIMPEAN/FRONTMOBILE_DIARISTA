package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address

data class AddressFormState(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val estado: String,
    val cidade: String,
    val numero: String,
    val isLoading : Boolean
)
fun createAddressFormState(address: Address): AddressFormState {
    return AddressFormState(
        cep = address.cep,
        logradouro = address.street,
        bairro =  address.district,
        estado = address.state,
        cidade = address.city,
        numero = address.number,
        isLoading = false
    )
}
fun AddressFormState.toDomain() : Address {
    return Address(
        cep = this.cep,
        street = this.logradouro,
        district = this.bairro,
        city = this.cidade,
        state = this.estado,
        number = this.numero,
        complement = null
    )
}