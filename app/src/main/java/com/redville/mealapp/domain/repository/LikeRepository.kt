package com.redville.mealapp.domain.repository

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dto.LikesResponse
import com.redville.mealapp.domain.model.Like

interface LikeRepository {

    fun saveLike(like: List<Like>): Either<Failure, Boolean>

    fun getLikeByUser(user: String): Either<Failure, LikesResponse>

    fun deleteLike(like: List<Like>): Either<Failure, Boolean>

}