package com.redville.mealapp.presentation.meals

import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.GetCategories
import com.redville.mealapp.domain.usecase.SaveCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

/*@DelicateCoroutinesApi
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsByName: GetCategories,
    private val saveMeals: SaveCategories
) : BaseViewModel() {

    fun doGetMealsByName(name: String) {
        getMealsByName(name) {
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

}*/