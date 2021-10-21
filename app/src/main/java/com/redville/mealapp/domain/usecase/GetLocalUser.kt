package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.core.plataform.AuthManager
import com.redville.mealapp.domain.model.User
import javax.inject.Inject

class GetLocalUser @Inject constructor(private val authManager: AuthManager) :
    UseCase<User, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, User> {
        val result = authManager.user

        return result?.let {
            Either.Right(it)
        } ?: Either.Left(Failure.Unauthorized)
    }

}