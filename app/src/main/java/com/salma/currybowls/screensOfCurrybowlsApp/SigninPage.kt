//package com.salma.currybowls.screensOfCurrybowlsApp
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.salma.currybowls.R
//import com.salma.currybowls.Currybowlscomponents.ButtonComponent
//import com.salma.currybowls.Currybowlscomponents.ClickableLoginTextComponent
//import com.salma.currybowls.Currybowlscomponents.DividerTextComponent
//import com.salma.currybowls.Currybowlscomponents.HeadingTextComponent
//import com.salma.currybowls.Currybowlscomponents.MyTextFieldComponent
//import com.salma.currybowls.Currybowlscomponents.PasswordTextFieldComponent
//import com.salma.currybowls.Currybowlsdata.ViewModelOfLogin
//import com.salma.currybowls.Currybowlsdata.Currybowlslogin.UIEventOfLogin
//import com.salma.currybowls.CurryBowlsRouter.AppRouter
//import com.salma.currybowls.CurryBowlsRouter.Screen
//import com.salma.currybowls.CurryBowlsRouter.SystemBackButtonHandler
//
//
//@Composable
//fun LoginScreen(loginViewModel: ViewModelOfLogin = viewModel()) {
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(28.dp)
//        ) {
//
//            Column( modifier = Modifier.fillMaxSize()) {
//                Image(
//                    modifier = Modifier.size(350.dp),
//                    painter = painterResource(id = R.drawable.cblogo),
//                    contentDescription = null)
//                Spacer(modifier = Modifier.height(0.dp))
//                HeadingTextComponent(value = "Login")
//                Spacer(modifier = Modifier.height(20.dp))
//
//                MyTextFieldComponent(labelValue = stringResource(id = com.salma.currybowls.R.string.email),
//                    painterResource(id = com.salma.currybowls.R.drawable.message),
//                    onTextChanged = { loginViewModel.onEvent(UIEventOfLogin.EmailChanged(it)) },
//                    errorStatus = loginViewModel.loginUIState.value.emailError
//                )
//
//                PasswordTextFieldComponent(
//                    labelValue = stringResource(id =com.salma.currybowls. R.string.password),
//                    painterResource(id = com.salma.currybowls.R.drawable.lock),
//                    onTextSelected = {
//                        loginViewModel.onEvent(UIEventOfLogin.PasswordChanged(it))
//                    },
//                    errorStatus = loginViewModel.loginUIState.value.passwordError
//                )
//
//                Spacer(modifier = Modifier.height(40.dp))
//
//                Spacer(modifier = Modifier.height(40.dp))
//
//                ButtonComponent(
//                    value = stringResource(id = com.salma.currybowls.R.string.login),
//                    onButtonClicked = {
//                       loginViewModel.onEvent(UIEventOfLogin.LoginButtonClicked)
//                    },
//                    isEnabled = loginViewModel.allValidationsPassed.value
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                DividerTextComponent()
//
//                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    AppRouter.navigateTo(Screen.SignUpScreen)
//                })
//            }
//        }
//
//        if(loginViewModel.loginInProgress.value) {
//            CircularProgressIndicator()
//        }
//    }
//
//    SystemBackButtonHandler {
//        AppRouter.navigateTo(Screen.SignUpScreen)
//    }
//
//}
//
//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}
//
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
import com.salma.currybowls.R
import com.salma.currybowls.Currybowlscomponents.ButtonComponent
import com.salma.currybowls.Currybowlscomponents.ClickableLoginTextComponent
import com.salma.currybowls.Currybowlscomponents.DividerTextComponent
import com.salma.currybowls.Currybowlscomponents.HeadingTextComponent
import com.salma.currybowls.Currybowlscomponents.MyTextFieldComponent
import com.salma.currybowls.Currybowlscomponents.PasswordTextFieldComponent
import com.salma.currybowls.Currybowlsdata.ViewModelOfLogin
import com.salma.currybowls.Currybowlsdata.Currybowlslogin.UIEventOfLogin
import com.salma.currybowls.CurryBowlsRouter.AppRouter
import com.salma.currybowls.CurryBowlsRouter.Screen
import com.salma.currybowls.CurryBowlsRouter.SystemBackButtonHandler


@Composable
fun LoginScreen(loginViewModel: ViewModelOfLogin = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.size(350.dp),
                    painter = painterResource(id = R.drawable.cblogo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(0.dp))
                HeadingTextComponent(value = "Login")
                Spacer(modifier = Modifier.height(20.dp))

                // Using TextField for email
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = { loginViewModel.onEvent(UIEventOfLogin.EmailChanged(it)) },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                // Using TextField for password
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(UIEventOfLogin.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(UIEventOfLogin.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false) {
                    AppRouter.navigateTo(Screen.SignUpScreen)
                }
            }
        }

        // Show a circular progress indicator when login is in progress
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }

    // Handle system back button press to navigate to SignUpScreen
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
