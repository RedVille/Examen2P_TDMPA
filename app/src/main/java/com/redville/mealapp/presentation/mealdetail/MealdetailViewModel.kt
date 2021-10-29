package com.redville.mealapp.presentation.mealdetail

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.*
import com.redville.mealapp.presentation.account.AccountViewState
import com.redville.mealapp.presentation.likes.LikesViewState
import com.redville.mealapp.presentation.random.MealViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealdetailViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val saveLike: SaveLike,
    private val getLikeByUser: GetLikeByUser,
    private val deleteLike: DeleteLike,
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

    fun saveLike(like: List<Like>) {
        saveLike(like) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

    fun deleteLike(like: List<Like>) {
        deleteLike(like) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

    //endregion

}