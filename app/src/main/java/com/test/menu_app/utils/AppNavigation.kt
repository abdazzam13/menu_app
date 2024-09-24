package com.test.menu_app.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.menu_app.view.MealByCategoryScreen
import com.test.menu_app.view.MealCategoryScreen
import com.test.menu_app.viewmodel.MenuViewModel

@Composable
fun AppNavigation(menuViewModel: MenuViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.mealCategory ){
        composable(Routes.mealCategory) {
                MealCategoryScreen(menuViewModel = menuViewModel, navController = navController)
        }
        composable(Routes.mealByCategory + "/{category}") {
            val category = it.arguments?.getString("category")
            if (category != null) {
                MealByCategoryScreen(menuViewModel = menuViewModel,category = category,  navController = navController)
            }
        }
    }
}