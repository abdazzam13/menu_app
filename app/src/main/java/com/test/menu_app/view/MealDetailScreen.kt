package com.test.menu_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.test.menu_app.R
import com.test.menu_app.model.meal_detail.MealRecipe
import com.test.menu_app.utils.Routes
import com.test.menu_app.view.components.AppBar
import com.test.menu_app.view.components.Loading
import com.test.menu_app.view.components.VideoPlayer
import com.test.menu_app.viewmodel.MenuViewModel


@Composable
fun MealDetailScreen(navController: NavController, menuViewModel: MenuViewModel, id: String) {
    Column(
    ) {
        AppBar(name = stringResource(R.string.meal_detail))
        MealDetail(viewModel = menuViewModel, id = id)
    }
}

@Composable
fun MealDetail(viewModel: MenuViewModel, id: String) {
    val mealDetails by viewModel.mealDetail.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMealDetail(id = id)
    }
    if (mealDetails?.meals.isNullOrEmpty()) {
        Loading()
    } else {
        var meal = mealDetails?.meals as List<MealRecipe>
        meal.let {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                items(it) {
                    MealImageandDesc(mealRecipe = it)
                    Spacer(modifier = Modifier.padding(10.dp))
                    Recipe(mealRecipe = it)
                    Spacer(modifier = Modifier.padding(10.dp))
                    CookingInstruction(mealRecipe = it)
                }
            }
        }
    }
}

@Composable
fun MealImageandDesc(mealRecipe: MealRecipe) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = mealRecipe.strMeal,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = rememberAsyncImagePainter(mealRecipe.strMealThumb),
            contentDescription = "Meal: ${mealRecipe.strMeal}",
            modifier = Modifier.size(height = 200.dp, width = 600.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Text(
                text = stringResource(R.string.category, mealRecipe.strCategory)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(R.string.nation, mealRecipe.strArea)
            )
        }
    }
}

@Composable
fun Recipe(mealRecipe: MealRecipe) {
    val listMeasure = arrayListOf<String?>(
        mealRecipe.strMeasure1,
        mealRecipe.strMeasure2,
        mealRecipe.strMeasure3,
        mealRecipe.strMeasure4,
        mealRecipe.strMeasure5,
        mealRecipe.strMeasure6,
        mealRecipe.strMeasure7,
        mealRecipe.strMeasure8,
        mealRecipe.strMeasure9,
        mealRecipe.strMeasure10,
        mealRecipe.strMeasure11,
        mealRecipe.strMeasure12,
        mealRecipe.strMeasure13,
        mealRecipe.strMeasure14,
        mealRecipe.strMeasure15,
        mealRecipe.strMeasure16,
        mealRecipe.strMeasure17,
        mealRecipe.strMeasure18,
        mealRecipe.strMeasure19,
        mealRecipe.strMeasure20,
    );

    val listIngredients = arrayListOf<String?>(
        mealRecipe.strIngredient1,
        mealRecipe.strIngredient2,
        mealRecipe.strIngredient3,
        mealRecipe.strIngredient4,
        mealRecipe.strIngredient5,
        mealRecipe.strIngredient6,
        mealRecipe.strIngredient7,
        mealRecipe.strIngredient8,
        mealRecipe.strIngredient9,
        mealRecipe.strIngredient10,
        mealRecipe.strIngredient11,
        mealRecipe.strIngredient12,
        mealRecipe.strIngredient13,
        mealRecipe.strIngredient14,
        mealRecipe.strIngredient15,
        mealRecipe.strIngredient16,
        mealRecipe.strIngredient17,
        mealRecipe.strIngredient18,
        mealRecipe.strIngredient19,
        mealRecipe.strIngredient20,
    )
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.recipe),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        for (i in 1..19) {
            if (!listIngredients[i].isNullOrEmpty() && !listMeasure[i].isNullOrEmpty()) {
                Text("$i. ${listMeasure[i]} ${listIngredients[i]}")
            }
        }
    }
}

@Composable
fun CookingInstruction(mealRecipe: MealRecipe) {
    Column {
        Text(
            text = stringResource(R.string.cooking_instructions),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        VideoPlayer(url = mealRecipe.strYoutube)
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = mealRecipe.strInstructions, textAlign = TextAlign.Justify
        )
    }
}