package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.UsersResponse
import com.redville.mealapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val userRepository: UserRepository) :
    UseCase<UsersResponse, Int>() {

    override suspend fun run(params: Int) = userRepository.getUserById(params)

}