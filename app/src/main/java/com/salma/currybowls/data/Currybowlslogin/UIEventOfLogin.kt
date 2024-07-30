package com.salma.currybowls.data.Currybowlslogin

sealed class UIEventOfLogin{

    data class EmailChanged(val email:String): UIEventOfLogin()
    data class PasswordChanged(val password: String) : UIEventOfLogin()

    object LoginButtonClicked : UIEventOfLogin()
}
