package com.redville.mealapp.presentation.account

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.User

class AccountViewState : BaseViewState() {

    data class LoggedUser(val user: User) : BaseViewState()

    object UserNotFound : BaseViewState()

}