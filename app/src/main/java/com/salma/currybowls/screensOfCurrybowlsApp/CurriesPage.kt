package com.salma.currybowls.screensOfCurrybowlsApp

//import UserProfileActivity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salma.currybowls.DetailsPageActivity

import com.salma.currybowls.R
import com.salma.currybowls.UserProfileActivity
import com.salma.currybowls.Currybowlscomponents.AppToolbar
import com.salma.currybowls.Currybowlscomponents.NavigationDrawerBody
import com.salma.currybowls.Currybowlscomponents.NavigationDrawerHeader

import com.salma.currybowls.Currybowlsdata.Currybowlshomemodel.CurryBowlsHViewmodel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: CurryBowlsHViewmodel = viewModel()) {


}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}