package com.redville.mealapp.domain.repository

import com.redville.mealapp.core.exception.Failure
import com.redville.mealapp.core.functional.Either
import com.redville.mealapp.data.dto.CategoriesResponse
import com.redville.mealapp.domain.model.Category

interface CategoryRepository {
    // CATEGORIES

    fun getCategories(): Either<Failure, CategoriesResponse>

    fun saveCategories(categories: List<Category>): Either<Failure, Boolean>

}