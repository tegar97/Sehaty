package com.miftah.sehaty.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.miftah.sehaty.R
import com.miftah.sehaty.ui.screens.navGraph.Route

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.food_onboarding_1),
            contentDescription = null
        )
    }
}