package com.redville.mealapp.presentation.account.login

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.User

sealed class UserViewState: BaseViewState() {

    data class UserReceived(val user: User): BaseViewState()

}