package com.salma.currybowls.data.CurrybowlssignupModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.salma.currybowls.data.UIStateForRegistration
import com.salma.currybowls.data.Procedures.Validator
import com.salma.currybowls.navigations.AppRouter
import com.salma.currybowls.navigations.Screen


class ViewModelOfSignup : ViewModel() {

    private val TAG = ViewModelOfSignup::class.simpleName
    var registrationUIState = mutableStateOf(UIStateForRegistration())
    var allValidationsPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)


    fun onEvent(event: UIEventOfSignUp) {
        when (event) {
            is UIEventOfSignUp.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(firstName = event.firstName)
            }


            is UIEventOfSignUp.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(lastName = event.lastName)
            }


            is UIEventOfSignUp.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(email = event.email)
            }

            is UIEventOfSignUp.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(password = event.password)
            }

            is UIEventOfSignUp.RegisterButtonClicked -> {
                signUp()
            }

            is UIEventOfSignUp.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(privacyPolicyAccepted = event.status)
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(fName = registrationUIState.value.firstName)

        val lNameResult = Validator.validateLastName(lName = registrationUIState.value.lastName)

        val emailResult = Validator.validateEmail(email = registrationUIState.value.email)

        val passwordResult = Validator.validatePassword(password = registrationUIState.value.password)

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(statusValue = registrationUIState.value.privacyPolicyAccepted)

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value = fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status

    }



    private fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { signUpInProgress.value = false
                if (it.isSuccessful) { AppRouter.navigateTo(Screen.HomeScreen) }
            }.addOnFailureListener {


            }
    }
}
