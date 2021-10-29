package com.redville.mealapp.domain.repository

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dto.UsersResponse
import com.redville.mealapp.domain.model.User

interface UserRepository {

    fun saveUser(user: List<User>): Either<Failure, Boolean>

    fun getUserByName(name: String): Either<Failure, UsersResponse>

}