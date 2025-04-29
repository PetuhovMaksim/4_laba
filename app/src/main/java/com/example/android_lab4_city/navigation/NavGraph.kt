package com.example.android_lab4_city.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_lab4_city.domain.CityViewModel
import com.example.android_lab4_city.ui.screens.*

@Composable
fun CityNavGraph(viewModel: CityViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CityScreen.Categories.name,
        enterTransition = { slideInHorizontally(initialOffsetX = { 10000 }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -10000 }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -10000 }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 10000 }) }
    ) {
        composable(route = CityScreen.Categories.name) {
            CategoriesScreen(
                categories = viewModel.uiState.collectAsState().value.categories,
                onCategoryClick = { navController.navigate("${CityScreen.Places.name}/$it") }
            )
        }

        composable(
            route = "${CityScreen.Places.name}/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            PlacesScreen(
                categoryName = categoryName ?: "Unknown",
                places = categoryName?.let { viewModel.getPlacesByCategory(it) } ?: emptyList(),
                onPlaceClick = { navController.navigate("${CityScreen.PlaceDetail.name}/$it") },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${CityScreen.PlaceDetail.name}/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId")
            PlaceDetailScreen(
                place = placeId?.let { viewModel.getPlaceById(it) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}