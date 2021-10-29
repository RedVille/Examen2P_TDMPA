package com.redville.mealapp.data.source

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dao.MealDao
import com.redville.mealapp.data.dto.LikesResponse
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val mealDao: MealDao
) : LikeRepository {

    override fun saveLike(like: List<Like>): Either<Failure, Boolean> {

        val result = mealDao.saveLike(like)
        return if (result.size == like.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)

    }

    override fun getLikeByUser(user: String): Either<Failure, LikesResponse> {

        return Either.Right(LikesResponse(mealDao.getLikeByUser(user)))

    }

    override fun deleteLike(like: List<Like>): Either<Failure, Boolean> {

        val result = mealDao.deleteLike(like)
        return if (result > 0) Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }

}