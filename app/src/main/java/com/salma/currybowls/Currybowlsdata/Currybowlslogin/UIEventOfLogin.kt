package com.salma.currybowls.Currybowlsdata.Currybowlslogin

sealed class UIEventOfLogin{

    data class EmailChanged(val email:String): UIEventOfLogin()
    data class PasswordChanged(val password: String) : UIEventOfLogin()

    object LoginButtonClicked : UIEventOfLogin()
}
