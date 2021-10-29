package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.plataform.AuthManager
import javax.inject.Inject

class RemovePrefs @Inject constructor(private val authManager: AuthManager) :
    UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, Boolean> {
        authManager.user = null
        return true.let {
            Either.Right(it)
        }
    }

}