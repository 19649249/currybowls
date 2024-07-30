package com.salma.currybowls.screensOfCurrybowlsApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salma.currybowls.Currybowlscomponents.ButtonComponent
import com.salma.currybowls.Currybowlscomponents.CheckboxComponent
import com.salma.currybowls.Currybowlscomponents.ClickableLoginTextComponent
import com.salma.currybowls.Currybowlscomponents.DividerTextComponent
import com.salma.currybowls.Currybowlscomponents.HeadingTextComponent
import com.salma.currybowls.Currybowlscomponents.MyTextFieldComponent
import com.salma.currybowls.Currybowlscomponents.PasswordTextFieldComponent
import com.salma.currybowls.Currybowlsdata.CurrybowlssignupModel.UIEventOfSignUp
import com.salma.currybowls.Currybowlsdata.CurrybowlssignupModel.ViewModelOfSignup
import com.salma.currybowls.CurryBowlsRouter.AppRouter
import com.salma.currybowls.CurryBowlsRouter.Screen


@Composable
fun SignUpScreen(signupViewModel: ViewModelOfSignup = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(
                    modifier = Modifier.size(220.dp),
                    painter = painterResource(id = com.salma.currybowls.R.drawable.cblogo),
                    contentDescription = null)
                  HeadingTextComponent(value = "CurryBowls App")
                  Spacer(modifier = Modifier.height(8.dp))
                  MyTextFieldComponent(
                    labelValue = stringResource(id = com.salma.currybowls.R.string.first_name),
                    painterResource(id =com.salma.currybowls. R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(UIEventOfSignUp.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.salma.currybowls.R.string.last_name),
                    painterResource = painterResource(id =com.salma.currybowls. R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(UIEventOfSignUp.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id =com.salma.currybowls. R.string.email),
                    painterResource = painterResource(id = com.salma.currybowls.R.drawable.message),
                    onTextChanged = {
                        signupViewModel.onEvent(UIEventOfSignUp.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = com.salma.currybowls.R.string.password),
                    painterResource = painterResource(id = com.salma.currybowls.R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(UIEventOfSignUp.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = com.salma.currybowls.R.string.terms_and_conditions),
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        signupViewModel.onEvent(UIEventOfSignUp.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                ButtonComponent(
                    value = stringResource(id =com.salma.currybowls. R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(UIEventOfSignUp.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    AppRouter.navigateTo(Screen.LoginScreen)
                })
            }
        }


        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }

}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}