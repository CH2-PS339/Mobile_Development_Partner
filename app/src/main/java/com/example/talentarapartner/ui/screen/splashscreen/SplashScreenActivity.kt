package com.example.talentarapartner.ui.screen.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.talentarapartner.R
import com.example.talentarapartner.ui.main.TalentaraPartnerApp
import com.example.talentarapartner.ui.navigation.Screen
import com.example.talentarapartner.ui.theme.TalentaraPartnerTheme
import com.example.talentarapartner.ui.theme.logo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.talentara_logo),
            contentDescription = null,
            modifier = modifier
                .size(100.dp)
                .padding(bottom = 8.dp)
                .align(Alignment.Center)
        )
        Column (
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.talentara_text),
                contentDescription = null,
                modifier = modifier
                    .padding(bottom = 8.dp)
                    .size(width = 100.dp, height = 10.dp)
            )
            Text(
                text = "Partner",
                modifier = modifier.align(CenterHorizontally),
                color = logo,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp
            )
        }

    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
}
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Main.route) {
            TalentaraPartnerApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TalentaraPartnerTheme {
        SplashScreen(navController =  rememberNavController())
    }
}