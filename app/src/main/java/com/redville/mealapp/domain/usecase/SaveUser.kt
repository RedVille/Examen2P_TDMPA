package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUser @Inject constructor(private val userRepository: UserRepository) :
    UseCase<Boolean, List<User>>() {

    override suspend fun run(params: List<User>) = userRepository.saveUser(params)

}