package com.redville.mealapp.presentation.account.signup

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.User

sealed class UsersViewState: BaseViewState() {

    data class UsersReceived(val users: List<User>): BaseViewState()

}