package com.redville.mealapp.data.source

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dao.MealDao
import com.redville.mealapp.data.dto.UsersResponse
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mealDao: MealDao
) : UserRepository {

    override fun saveUser(user: List<User>): Either<Failure, Boolean> {

        val result = mealDao.saveUser(user)
        return if (result.size == user.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)

    }

    override fun getUserByName(name: String): Either<Failure, UsersResponse> {

        return Either.Right(UsersResponse(mealDao.getUserByName(name)))

    }

}