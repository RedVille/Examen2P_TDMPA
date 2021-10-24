package com.redville.mealapp.presentation.random

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.Meal

sealed class MealViewState: BaseViewState() {

    data class MealReceived(val meal: List<Meal>): BaseViewState()

}