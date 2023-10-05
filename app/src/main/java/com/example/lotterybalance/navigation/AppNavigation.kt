package com.example.lotterybalance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lotterybalance.database.dao.BoletoDao
import com.example.lotterybalance.database.dao.PremioDao
import com.example.lotterybalance.presentation.FirstScreen
import com.example.lotterybalance.presentation.SecondScreen
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val boletoModel: BoletoViewModel = hiltViewModel()
    val lista = boletoModel.sortidoBoletos
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {

        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)

        }
        composable(
            route = AppScreens.SecondScreen.route ) {
            SecondScreen(navController, lista)

        }
    }

}
