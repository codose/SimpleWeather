package com.android.weathersimple.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("homeScreen")
    object Details : Routes("detailsScreen/{weatherItem}") {
        fun createRoute(item: String) = "detailsScreen/$item"
    }
    object Favourite : Routes("favouriteScreen")
}
