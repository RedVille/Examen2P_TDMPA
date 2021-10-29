package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.LikesResponse
import com.redville.mealapp.domain.repository.LikeRepository
import javax.inject.Inject

class GetLikeByUser @Inject constructor(private val likeRepository: LikeRepository) :
    UseCase<LikesResponse, String>() {

    override suspend fun run(params: String) = likeRepository.getLikeByUser(params)

}