package com.redville.mealapp.presentation.mealdetail

import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.GetMealById
import com.redville.mealapp.domain.usecase.SaveMeals
import com.redville.mealapp.presentation.random.MealViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealdetailViewModel @Inject constructor(
    private val getMealById: GetMealById,
    private val saveMeals: SaveMeals
) :
    BaseViewModel() {

    fun doGetMealById(id: Int) {
        getMealById(id) {
            it.fold(::handleFailure) {
                state.value = MealViewState.MealReceived(it.meals ?: listOf())

                saveMeals(it.meals ?: listOf())

                true
            }
        }
    }

    private fun saveMeals(meals: List<Meal>) {
        saveMeals(meals) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

}