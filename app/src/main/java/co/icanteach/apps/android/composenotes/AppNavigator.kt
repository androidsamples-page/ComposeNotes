package co.icanteach.apps.android.composenotes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.icanteach.apps.android.composenotes.detail.DetailScreen
import co.icanteach.apps.android.composenotes.home.HomeScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.DetailScreen.route
        ) {
            DetailScreen(
                navController = navController
            )
        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object DetailScreen : Screens("detail_screen")
}