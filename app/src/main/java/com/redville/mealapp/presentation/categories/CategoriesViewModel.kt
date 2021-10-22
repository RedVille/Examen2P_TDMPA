package com.redville.mealapp.presentation.categories

import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.usecase.GetCategories
import com.redville.mealapp.domain.usecase.SaveCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val saveCategories: SaveCategories
) : BaseViewModel() {

    fun doGetCategories(name: String) {
        getCategories(name) {
            it.fold(::handleFailure) {
                state.value = CategoriesViewState.CategoriesReceived(it.categories ?: listOf())

                saveCategories(it.categories ?: listOf())

                true
            }
        }
    }

    private fun saveCategories(categories: List<Category>) {
        saveCategories(categories) {
            it.fold((::handleFailure)) {
                it
            }
        }
    }

}