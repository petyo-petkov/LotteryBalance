package com.example.lotterybalance.presentation.secondScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lotterybalance.navigation.AppScreens

@SuppressLint("UnrememberedMutableState")
@Composable
fun SecBottomBar(navController: NavController) {

    BottomAppBar(
        actions = {
            // delete
            IconButton(
                onClick = {
                    navController.navigate(route = AppScreens.FirstScreen.route){
                        popUpTo("first_screen"){inclusive=true}
                    }
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }

        },
        modifier = Modifier,
        containerColor = Color(0xFFFFCCBC),
        contentColor = Color.Black,
        contentPadding = PaddingValues(horizontal = 6.dp)
    )


}