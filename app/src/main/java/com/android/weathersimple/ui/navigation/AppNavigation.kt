package com.android.weathersimple.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.ui.screens.DetailsScreen
import com.android.weathersimple.ui.screens.FavouriteScreen
import com.android.weathersimple.ui.screens.HomeScreen
import com.android.weathersimple.utils.AssetParamType
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen {
                val json = Uri.encode(Gson().toJson(it))
                navController.navigate(Routes.Details.createRoute(json))
            }
        }

        composable(
            route = Routes.Details.route,
            arguments = listOf(
                navArgument("weatherItem") {
                    type = AssetParamType()
                }
            )
        ) {
            val item = it.arguments?.getParcelable<WeatherItem>("weatherItem")

            requireNotNull(item) { "Item not found" }

            DetailsScreen(item) {
                navController.popBackStack()
            }
        }

        composable(route = Routes.Favourite.route) {
            FavouriteScreen()
        }
    }
}
