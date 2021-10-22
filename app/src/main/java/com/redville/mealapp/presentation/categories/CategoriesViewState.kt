package com.redville.mealapp.presentation.categories

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.Category

sealed class CategoriesViewState: BaseViewState() {

    data class CategoriesReceived(val categories: List<Category>): BaseViewState()

}