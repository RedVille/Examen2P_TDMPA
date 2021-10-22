package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.repository.CategoryRepository
import com.redville.mealapp.domain.repository.MealRepository
import javax.inject.Inject

class SaveCategories @Inject constructor(private val categoryRepository: CategoryRepository) :
    UseCase<Boolean, List<Category>>() {

    override suspend fun run(params: List<Category>) = categoryRepository.saveCategories(params)

}