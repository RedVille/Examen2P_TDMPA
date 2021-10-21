package com.redville.mealapp.data.source

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.core.plataform.NetworkHandler
import com.redville.mealapp.data.api.MealApi
import com.redville.mealapp.data.dao.MealDao
import com.redville.mealapp.data.dto.MealsResponse
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.repository.MealRepository
import com.redville.mealapp.framework.api.ApiRequest
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao,
    private val networkHandler: NetworkHandler
) :
    MealRepository, ApiRequest {

    override fun getMealsByName(name: String) : Either<Failure, MealsResponse> {
        val result = makeRequest(networkHandler, mealApi.getCocktailsByName(name), { it }, MealsResponse(emptyList()))

        return if (result.isLeft) {

            val localResult = mealDao.getMealsByName("%$name%")

            if (localResult.isEmpty()) result
            else Either.Right(MealsResponse(localResult))

        } else result

    }

    override fun saveMeal(meals: List<Meal>): Either<Failure, Boolean> {
        val result = mealDao.saveMeals(meals)
        return if (result.size == meals.size) Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }

    override fun updateMeal(meal: Meal): Either<Failure, Boolean> {
        TODO("Not yet implemented")
    }


}