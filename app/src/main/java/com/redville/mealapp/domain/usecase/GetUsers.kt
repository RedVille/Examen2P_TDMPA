package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.UsersResponse
import com.redville.mealapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsers @Inject constructor(private val userRepository: UserRepository) :
    UseCase<UsersResponse, String>() {
        override suspend fun run(params: String): Either<Failure, UsersResponse> = userRepository.getUsers()
}