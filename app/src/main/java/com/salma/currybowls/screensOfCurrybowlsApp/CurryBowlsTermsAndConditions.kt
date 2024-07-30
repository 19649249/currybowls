package com.salma.currybowls.screensOfCurrybowlsApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salma.currybowls.R
import com.salma.currybowls.Currybowlscomponents.HeadingTextComponent
import com.salma.currybowls.Currybowlscomponents.NormalTextComponent
import com.salma.currybowls.CurryBowlsRouter.AppRouter
import com.salma.currybowls.CurryBowlsRouter.Screen
import com.salma.currybowls.CurryBowlsRouter.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        Image(
            modifier = Modifier.size(350.dp),
            painter = painterResource(id = R.drawable.cblogo),
            contentDescription = null
        )


        HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
        }
    NormalTextComponent(value = stringResource(id = R.string.terms_and_conditions_headerdes))

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}