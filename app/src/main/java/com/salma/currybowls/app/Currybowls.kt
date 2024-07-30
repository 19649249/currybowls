package com.salma.currybowls.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salma.currybowls.data.Currybowlshomemodel.CurryBowlsHViewmodel
import com.salma.currybowls.navigations.AppRouter
import com.salma.currybowls.navigations.Screen
import com.salma.currybowls.screensOfCurrybowlsApp.HomeScreen
import com.salma.currybowls.screensOfCurrybowlsApp.LoginScreen
import com.salma.currybowls.screensOfCurrybowlsApp.SignUpScreen
import com.salma.currybowls.screensOfCurrybowlsApp.TermsAndConditionsScreen


@Composable
fun CurryBowls(homeViewModel: CurryBowlsHViewmodel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            AppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }

    }
}