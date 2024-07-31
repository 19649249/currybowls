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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salma.currybowls.DetailsPageActivity

import com.salma.currybowls.R
import com.salma.currybowls.UserProfileActivity
import com.salma.currybowls.components.AppToolbar
import com.salma.currybowls.components.NavigationDrawerBody
import com.salma.currybowls.components.NavigationDrawerHeader

import com.salma.currybowls.data.Currybowlshomemodel.CurryBowlsHViewmodel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: CurryBowlsHViewmodel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getUserData()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(homeViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                onNavigationItemClicked = {
                    Log.d("ComingHere","inside_NavigationItemClicked")
                    Log.d("ComingHere","${it.itemId} ${it.title}")
                })
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        )
        {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 640.dp)
                        .background(color = Color.White)
                )
                {
                    val localContext = LocalContext.current


                    Image(
                        painter = painterResource(id = R.drawable.prof),
                        contentDescription = " ",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 20.dp,
                                y = 26.dp
                            )
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, UserProfileActivity::class.java)
                                )
                            }
                            .requiredSize(size = 42.dp)
                            .clip(shape = CircleShape)
                            .border(
                                border = BorderStroke(0.10000000149011612.dp, Color(0xffbdbdbd)),
                                shape = CircleShape
                            )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.chickencurry),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 21.dp,
                                y = 256.dp
                            )
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, DetailsPageActivity::class.java)
                                )
                            }
                            .requiredWidth(width = 180.dp)
                            .requiredHeight(height = 200.dp)
                            .clip(shape = RoundedCornerShape(15.dp)
                            )
                    )
//






                }

            }
        }
    }




}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}