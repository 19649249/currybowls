package  com.salma.currybowls.data.CurrybowlssignupModel

sealed class UIEventOfSignUp{

    data class FirstNameChanged(val firstName:String) : UIEventOfSignUp()
    data class LastNameChanged(val lastName:String) : UIEventOfSignUp()
    data class EmailChanged(val email:String): UIEventOfSignUp()
    data class PasswordChanged(val password: String) : UIEventOfSignUp()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : UIEventOfSignUp()

    object RegisterButtonClicked : UIEventOfSignUp()
}
