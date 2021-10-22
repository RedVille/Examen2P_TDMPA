package com.redville.mealapp.data.dto

import com.redville.mealapp.domain.model.Category
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(val categories: List<Category>? = listOf())