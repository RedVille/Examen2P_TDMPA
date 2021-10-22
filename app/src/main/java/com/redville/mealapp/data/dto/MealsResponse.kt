package com.redville.mealapp.data.dto

import com.redville.mealapp.domain.model.Meal
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(val meals: List<Meal>? = listOf())
