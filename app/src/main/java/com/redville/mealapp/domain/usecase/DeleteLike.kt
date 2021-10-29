package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.repository.LikeRepository
import javax.inject.Inject

class DeleteLike @Inject constructor(private val likeRepository: LikeRepository) :
    UseCase<Boolean, List<Like>>() {

    override suspend fun run(params: List<Like>) = likeRepository.deleteLike(params)

}