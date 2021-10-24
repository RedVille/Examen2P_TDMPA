package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.MealsResponse
import com.redville.mealapp.domain.repository.MealRepository
import javax.inject.Inject

class GetMealById @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<MealsResponse, Int>() {

    override suspend fun run(params: Int) = mealRepository.getMealById(params)

}