package com.redville.mealapp.presentation.account

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.usecase.GetLocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class AccountViewModel @Inject constructor(private val getLocalUser: GetLocalUser) :
    BaseViewModel() {

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

}