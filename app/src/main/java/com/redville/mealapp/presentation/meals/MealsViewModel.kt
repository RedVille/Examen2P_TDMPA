package com.redville.mealapp.presentation.meals

import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.GetMealById
import com.redville.mealapp.domain.usecase.GetMeals
import com.redville.mealapp.domain.usecase.GetMealsByName
import com.redville.mealapp.domain.usecase.SaveMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMeals: GetMeals,
    private val getMealsByName: GetMealsByName,
    private val getMealById: GetMealById,
    private val saveMeals: SaveMeals
) : BaseViewModel() {

    fun doGetMeals(category: String) {
        getMeals(category) {
            it.fold(::handleFailure) {
                state.value = MealsViewState.MealsReceived(it.meals ?: listOf())

                saveMeals(it.meals ?: listOf())

                true
            }
        }
    }

    fun doGetMealsByName(name: String) {
        getMealsByName(name) {
            it.fold(::handleFailure) {
                state.value = MealsViewState.MealsReceived(it.meals ?: listOf())

                saveMeals(it.meals ?: listOf())

                true
            }
        }
    }

    fun doGetMealsById(id: Int) {
        getMealById(id) {
            it.fold(::handleFailure) {
                state.value = MealsViewState.MealsReceived(it.meals ?: listOf())

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