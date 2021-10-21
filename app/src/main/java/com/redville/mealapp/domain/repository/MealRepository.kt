package com.redville.mealapp.domain.repository

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dto.MealsResponse
import com.redville.mealapp.domain.model.Meal

interface MealRepository {

    fun getMealsByName(name: String): Either<Failure, MealsResponse>

    fun saveMeal(meals: List<Meal>): Either<Failure, Boolean>

    fun updateMeal(meal: Meal): Either<Failure, Boolean>

}