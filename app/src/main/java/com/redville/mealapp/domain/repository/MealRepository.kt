package com.redville.mealapp.domain.repository

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dto.MealsResponse
import com.redville.mealapp.domain.model.Meal

interface MealRepository {

    // MEALS

    fun getMeals(category: String): Either<Failure, MealsResponse>

    fun getMealsByName(name: String): Either<Failure, MealsResponse>

    fun saveMeals(meals: List<Meal>): Either<Failure, Boolean>

}