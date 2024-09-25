package com.test.menu_app.model.repository

import com.test.menu_app.model.menu_category.MenuCategory
import com.test.menu_app.model.api.ApiConfig
import com.test.menu_app.model.meal_by_category.MealByCategory
import com.test.menu_app.model.meal_detail.MealDetail

class ApiRepository {
    private val apiService = ApiConfig().getApiService()

    suspend fun getCategory() : MenuCategory {
        return apiService.getCategory()
    }

    suspend fun getMealByCategory(category: String) : MealByCategory {
        return apiService.getMealByCategory(category)
    }

    suspend fun getMealRecipe(id: String) : MealDetail {
        return apiService.getMealRecipe(id)
    }
}