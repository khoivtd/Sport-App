package com.sport.app.feature_event.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sport.app.R
import com.sport.app.feature_event.presentation.navigation.BottomNavGraph
import com.sport.app.feature_event.presentation.navigation.Screen
import com.sport.app.utils.Constants.DESCRIPTION_ICON

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainComponent() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier
        .background(color = colorResource(id = R.color.primary))
        .padding(
            dimensionResource(id = R.dimen.dp_16)
        ), bottomBar = { BottomNavBar(navController) }) {
        BottomNavGraph(navController)
    }
}

@Composable
private fun BottomNavBar(
    navController: NavHostController,
) {
    val screens = listOf(Screen.TeamScreen, Screen.MatchScreen)
    val containerColor = colorResource(id = R.color.tertiary)
    val tintColor = colorResource(id = R.color.onSecondary)
    val selectedTintColor = colorResource(id = R.color.secondary)

    // Observe the current navigation route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.dp_68))
    ) {
        NavigationBar(containerColor = containerColor, content = {
            screens.forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                NavigationBarItem(selected = isSelected, onClick = {
                    navController.navigate(screen.route)
                }, label = {
                    Text(
                        text = stringResource(screen.labelResId),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, icon = {
                    Icon(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.dp_24)),
                        painter = painterResource(screen.icon),
                        contentDescription = DESCRIPTION_ICON,
                        tint = if (isSelected) selectedTintColor else tintColor
                    )
                }, colors = customNavigationBarItemColors(
                    containerColor, selectedTintColor, tintColor
                )
                )
            }
        })
    }
}

@Composable
private fun customNavigationBarItemColors(
    containerColor: Color, selectedColor: Color, unselectedColor: Color
): NavigationBarItemColors {
    return NavigationBarItemDefaults.colors(
        selectedIconColor = selectedColor,
        selectedTextColor = selectedColor,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        indicatorColor = containerColor
    )
}
