package com.example.lotterybalance.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lotterybalance.presentation.FirstScreen
import com.example.lotterybalance.presentation.SecondScreen
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.FirstScreen.route
    ){

        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)

        }
        composable(
            route = "${AppScreens.SecondScreen.route}/{startDay}/{endDay}",
            arguments = listOf(
                navArgument("startDay" ){type = NavType.LongType},
                navArgument("endDay"){type = NavType.LongType}
            )
            ) {
            val startDay = it.arguments?.getLong("startDay")
            val endDay = it.arguments?.getLong("endDay")
            requireNotNull(startDay)
            requireNotNull(endDay)
            SecondScreen(navController, startDay, endDay)

        }
    }

}
