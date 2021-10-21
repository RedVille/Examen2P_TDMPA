package com.redville.mealapp.core.presentation

import com.redville.mealapp.core.exception.Failure

interface OnFailure {

    fun handleFailure(failure: Failure?)

}