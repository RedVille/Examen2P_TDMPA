package com.redville.mealapp.presentation.account

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.usecase.GetLocalUser
import com.redville.mealapp.domain.usecase.RemovePrefs
import com.redville.mealapp.domain.usecase.SaveLocalUser
import com.redville.mealapp.presentation.account.signup.UsersViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val removePrefs: RemovePrefs
) :
    BaseViewModel() {

    fun getLocalUser() {
        getLocalUser(UseCase.None()) {
            it.fold(::userNotFound) {
                state.value = AccountViewState.LoggedUser(it)

                true
            }
        }
    }

    fun removeLocalUser() {
        removePrefs(params = UseCase.None()){
            it.fold(::handleFailure) {
                it
            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = AccountViewState.UserNotFound
        handleFailure(failure)
    }

}