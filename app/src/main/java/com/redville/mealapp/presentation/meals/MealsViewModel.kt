package com.redville.mealapp.presentation.meals

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.*
import com.redville.mealapp.presentation.account.AccountViewState
import com.redville.mealapp.presentation.likes.LikesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMeals: GetMeals,
    private val getMealsByName: GetMealsByName,
    private val getMealById: GetMealById,
    private val saveMeals: SaveMeals,
    private val getLocalUser: GetLocalUser,
    private val saveLike: SaveLike,
    private val getLikeByUser: GetLikeByUser,
    private val deleteLike: DeleteLike
) : BaseViewModel() {

    //region meals

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
            it.fold(::handleFailure) {
                it
            }
        }
    }

    //endregion

    //region account

    fun getLocalUser() {
        getLocalUser(UseCase.None()) {
            it.fold(::userNotFound) {
                state.value = AccountViewState.LoggedUser(it)

                true
            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = AccountViewState.UserNotFound
        handleFailure(failure)
    }

    //endregion

    //region Like

    fun doGetLikeByUser(string: String) {
        getLikeByUser(string) {
            it.fold(::handleFailure) {
                state.value = LikesViewState.LikesReceived(it.likes?: listOf())

                true
            }
        }
    }

    //endregion
}