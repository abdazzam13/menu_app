package com.test.menu_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.test.menu_app.R
import com.test.menu_app.model.menu_category.Category
import com.test.menu_app.utils.Routes
import com.test.menu_app.view.components.AppBar
import com.test.menu_app.view.components.Loading
import com.test.menu_app.viewmodel.MenuViewModel

@Composable
fun MealCategoryScreen(menuViewModel: MenuViewModel, navController: NavController) {
    Column() {
        AppBar(stringResource(R.string.food_category))
        ListMenuCategory(viewModel = menuViewModel, navController = navController)
    }
}

@Composable
fun ListMenuCategory(viewModel: MenuViewModel, navController: NavController) {
    val menuCategories by viewModel.menuCategories.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMenuCategory()
    }
    if (menuCategories?.categories.isNullOrEmpty()) {
        Loading()
    } else {
        var categories = menuCategories?.categories as List<Category>
        categories.let {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                items(it) {
                    ItemMenuCategory(
                        name = it.strCategory,
                        url = it.strCategoryThumb,
                        navController = navController
                    )
                }
            }
        }
    }
}
@Composable
fun ItemMenuCategory(
    name: String,
    url: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(Routes.mealByCategory + "/$name")
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Category: $name",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = name,
            modifier = modifier,
            fontWeight = FontWeight.Bold
        )
    }
}
