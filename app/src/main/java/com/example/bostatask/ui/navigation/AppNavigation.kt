package com.example.bostatask.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.bostatask.ui.screens.details.DetailsScreen
import com.example.bostatask.ui.screens.list.ListScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Route.ListScreen) {
        composable<Route.ListScreen> {
            ListScreen(
                onNavigateToDetails = { gameId ->
                    navController.navigate(Route.DetailsScreen(gameId = gameId))
                }
            )
        }
        composable<Route.DetailsScreen> { backStackEntry ->
            val detailsRoute: Route.DetailsScreen = backStackEntry.toRoute()
            DetailsScreen(
                gameId = detailsRoute.gameId,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
