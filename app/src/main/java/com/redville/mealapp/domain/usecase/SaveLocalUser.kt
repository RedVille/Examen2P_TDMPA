package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.plataform.AuthManager
import com.redville.mealapp.domain.model.User
import javax.inject.Inject

class SaveLocalUser @Inject constructor(private val authManager: AuthManager) :
    UseCase<Boolean, User>() {

    override suspend fun run(params: User): Either<Failure, Boolean> {
        authManager.user = params
        return true.let {
            Either.Right(it)
        }
    }

}