package com.redville.mealapp.domain.usecase

import com.redville.mealapp.core.interactor.UseCase
import com.redville.mealapp.data.dto.CategoriesResponse
import com.redville.mealapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(private val categoryRepository: CategoryRepository) :
    UseCase<CategoriesResponse, UseCase.None>() {

    override suspend fun run(params: None) = categoryRepository.getCategories()

}