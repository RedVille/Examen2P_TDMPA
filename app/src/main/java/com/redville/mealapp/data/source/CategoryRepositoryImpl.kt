package com.redville.mealapp.data.source

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.plataform.NetworkHandler
import com.redville.mealapp.data.api.MealApi
import com.redville.mealapp.data.dao.MealDao
import com.redville.mealapp.data.dto.CategoriesResponse
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.repository.CategoryRepository
import com.redville.mealapp.framework.api.ApiRequest
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao,
    private val networkHandler: NetworkHandler
) :
    CategoryRepository, ApiRequest {

    // CATEGORIES

    override fun getCategories() : Either<Failure, CategoriesResponse> {
        val result = makeRequest(networkHandler, mealApi.getCategories(), { it }, CategoriesResponse(emptyList()))

        return if (result.isLeft) {

            val localResult = mealDao.getCategories()

            if (localResult.isEmpty()) result
            else Either.Right(CategoriesResponse(localResult))

        } else result

    }

    override fun saveCategories(categories: List<Category>): Either<Failure, Boolean> {
        val result = mealDao.saveCategories(categories)
        return if (result.size == categories.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }

}