package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.repository.MealRepository
import javax.inject.Inject

class SaveMeals @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<Boolean, List<Meal>>() {

    override suspend fun run(params: List<Meal>) = mealRepository.saveMeals(params)

}