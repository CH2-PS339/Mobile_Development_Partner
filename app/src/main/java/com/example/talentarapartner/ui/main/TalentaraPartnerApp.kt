package com.example.talentarapartner.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.talentarapartner.R
import com.example.talentarapartner.ui.navigation.NavigationItem
import com.example.talentarapartner.ui.navigation.Screen
import com.example.talentarapartner.ui.screen.activity.ActiviyScreen
import com.example.talentarapartner.ui.screen.addproject.AddProjectScreen
import com.example.talentarapartner.ui.screen.home.HomeScreen
import com.example.talentarapartner.ui.screen.order.OrderScreen
import com.example.talentarapartner.ui.screen.profile.ProfileScreen
import com.example.talentarapartner.ui.theme.TalentaraPartnerTheme

@Composable
fun TalentaraPartnerApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.AddProject.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToAddproject = { navController.navigate(Screen.AddProject.route) })
            }
            composable(Screen.Order.route) {
                OrderScreen()
            }
            composable(Screen.Activity.route) {
                ActiviyScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.AddProject.route) {
                AddProjectScreen(
                    navigateBack = { navController.navigateUp() },
                    navigateBackToHome = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(16.dp)
            .height(65.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 26.dp,
                    topEnd = 26.dp,
                )
            ),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = painterResource(id = R.drawable.home_unselected),
                screen = Screen.Home,
                selectedIcon = painterResource(id = R.drawable.home_selected),
            ),
            NavigationItem(
                title = stringResource(id = R.string.orders),
                icon = painterResource(id = R.drawable.order_unselected),
                screen = Screen.Order,
                selectedIcon = painterResource(id = R.drawable.order_selected),
            ),
            NavigationItem(
                title = stringResource(id = R.string.activity),
                icon = painterResource(id = R.drawable.activity_unselected),
                screen = Screen.Activity,
                selectedIcon = painterResource(id = R.drawable.activity_selected)
            ),
            NavigationItem(
                title = stringResource(id = R.string.profile),
                icon = painterResource(id = R.drawable.profile_unselected),
                screen = Screen.Profile,
                selectedIcon = painterResource(id = R.drawable.profile_selected)
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                label = {
                    Text(
                        item.title,
                        fontWeight = if (currentRoute == item.screen.route) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 10.sp
                    )
                },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = if (currentRoute == item.screen.route) item.selectedIcon else item.icon,
                        contentDescription = item.title,
                        modifier = modifier
                            .size(20.dp)
                    )
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    TalentaraPartnerTheme {
        val navController = rememberNavController()
        BottomBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun TalentaraPartnerAppPreview() {
    TalentaraPartnerTheme {
        TalentaraPartnerApp()
    }
}