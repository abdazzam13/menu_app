package com.test.menu_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.test.menu_app.model.meal_by_category.Meals
import com.test.menu_app.utils.Routes
import com.test.menu_app.view.components.AppBar
import com.test.menu_app.view.components.Loading
import com.test.menu_app.viewmodel.MenuViewModel


@Composable
fun MealByCategoryScreen(
    menuViewModel: MenuViewModel,
    category: String,
    navController: NavController
) {
    Column() {
        AppBar(name = category)
        ListMeal(viewModel = menuViewModel, category = category, navController = navController)
    }
}

@Composable
fun ListMeal(viewModel: MenuViewModel, category: String, navController: NavController) {
    val meal by viewModel.mealByCategory.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMealByCategory(category)
    }
    if (meal?.meals.isNullOrEmpty()) {
        Loading()
    } else {
        var meals = meal?.meals as List<Meals>
        meals.let {
            LazyColumn {
                items(it) {
                    ItemMeal(
                        id = it.idMeal,
                        name = it.strMeal,
                        url = it.strMealThumb,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ItemMeal(
    id: String,
    name: String,
    url: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(Routes.mealDetail + "/$id")
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Meal: $name",
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = name,
            modifier = modifier,
            fontWeight = FontWeight.Bold
        )
    }
}



