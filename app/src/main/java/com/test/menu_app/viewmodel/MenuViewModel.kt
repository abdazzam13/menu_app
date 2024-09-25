package com.test.menu_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.menu_app.model.meal_by_category.MealByCategory
import com.test.menu_app.model.meal_detail.MealDetail
import com.test.menu_app.model.menu_category.MenuCategory
import com.test.menu_app.model.repository.ApiRepository
import com.test.menu_app.utils.Constants
import kotlinx.coroutines.launch
import java.lang.Exception

class MenuViewModel(private val repository: ApiRepository): ViewModel() {

    private val _menuCategories = MutableLiveData<MenuCategory>()
    val menuCategories: LiveData<MenuCategory> = _menuCategories

    private val _mealByCategory = MutableLiveData<MealByCategory>()
    val mealByCategory: LiveData<MealByCategory> = _mealByCategory

    private val _mealDetail = MutableLiveData<MealDetail>()
    val mealDetail: LiveData<MealDetail> = _mealDetail

    fun fetchMenuCategory(){
        viewModelScope.launch {
            try {
              val menuCat = repository.getCategory()
              _menuCategories.value = menuCat
            } catch (e: Exception){
                Log.e(Constants.TAG.MENU_VIEWMODEL_TAG, "fetchMenuCategory exception: ${e.message}")
            }
        }
    }

    fun fetchMealByCategory(category: String){
        viewModelScope.launch {
            try {
                val mealByCat = repository.getMealByCategory(category)
                _mealByCategory.value = mealByCat
            } catch (e: Exception){
                Log.e(Constants.TAG.MENU_VIEWMODEL_TAG, "fetchMealByCategory exception: ${e.message}")
            }
        }
    }

    fun fetchMealDetail(id: String){
        viewModelScope.launch {
            try {
                val mealDetail = repository.getMealRecipe(id)
                _mealDetail.value = mealDetail
            } catch (e: Exception){
                Log.e(Constants.TAG.MENU_VIEWMODEL_TAG, "fetchMealDetail exception: ${e.message}")
            }
        }
    }
}