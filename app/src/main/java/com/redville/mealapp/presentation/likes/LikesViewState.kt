package com.redville.mealapp.presentation.likes

import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.domain.model.Like

sealed class LikesViewState : BaseViewState() {

    data class LikesReceived(val likes: List<Like>): BaseViewState()

}