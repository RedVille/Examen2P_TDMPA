package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.MealsResponse
import com.redville.mealapp.domain.repository.MealRepository
import javax.inject.Inject

class GetMeals @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<MealsResponse, String>() {

    override suspend fun run(params: String) = mealRepository.getMeals(params)

}