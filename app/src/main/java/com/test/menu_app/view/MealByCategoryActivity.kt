package com.test.menu_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.test.menu_app.model.meal_by_category.Meal
import com.test.menu_app.viewmodel.MenuViewModel


    @Composable
    fun MealByCategoryScreen(menuViewModel: MenuViewModel, category: String, navController: NavController) {
        Column(

        ) {
            ListMeal(viewModel = menuViewModel, category = category)
        }
    }

    @Composable
    fun ItemMeal(name: String, url: String, modifier: Modifier = Modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = "Category: $name",
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = name,
                modifier = modifier
            )
        }
    }

    @Composable
    fun ListMeal(viewModel: MenuViewModel, category: String) {
        val meal by viewModel.mealByCategory.observeAsState()

        LaunchedEffect(Unit){
            viewModel.fetchMealByCategory(category)
        }
        if (meal?.meals.isNullOrEmpty()){
            Text(text = "Loading...")
        } else {
            var meals = meal?.meals as List<Meal>
            meals.let {
                LazyColumn{
                    items(it){
                        ItemMeal(name = it.strMeal, url = it.strMealThumb)
                    }
                }
            }
        }
    }



