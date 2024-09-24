package com.test.menu_app.model.api

import com.test.menu_app.model.meal_by_category.MealByCategory
import com.test.menu_app.model.menu_category.MenuCategory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getCategory(): MenuCategory

    @GET("filter.php?")
    suspend fun getMealByCategory(@Query("c") category: String): MealByCategory
}