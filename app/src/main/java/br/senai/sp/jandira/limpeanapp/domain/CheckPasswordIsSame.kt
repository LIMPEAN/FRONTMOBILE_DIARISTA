package br.senai.sp.jandira.limpeanapp.domain



fun CheckPasswordIsSame(password : String, confirmPassword : String) : Boolean{
    return password == confirmPassword
}