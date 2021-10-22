package com.redville.mealapp.presentation.meals

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.Meal

sealed class MealsViewState : BaseViewState() {

    data class MealsReceived(val meals: List<Meal>): BaseViewState()

}