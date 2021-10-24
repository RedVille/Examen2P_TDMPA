package com.redville.mealapp.presentation.random

import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.GetRandomMeal
import com.redville.mealapp.domain.usecase.SaveMeals
import com.redville.mealapp.presentation.categories.CategoriesViewState
import com.redville.mealapp.presentation.meals.MealsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class RandomViewModel @Inject constructor(
    private val getRandomMeal: GetRandomMeal,
    private val saveMeals: SaveMeals
) :
    BaseViewModel() {

    fun doGetRandomMeal(name: String) {
        getRandomMeal(name) {
            it.fold(::handleFailure) {
                state.value = MealViewState.MealReceived(it.meals ?: listOf())

                saveMeals(it.meals ?: listOf())

                true
            }
        }
    }

    private fun saveMeals(meals: List<Meal>) {
        saveMeals(meals) {
            it.fold((::handleFailure)) {
                it
            }
        }
    }

}