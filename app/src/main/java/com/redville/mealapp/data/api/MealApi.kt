package com.redville.mealapp.data.api

import com.redville.mealapp.data.dto.CategoriesResponse
import com.redville.mealapp.data.dto.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("json/v1/1/categories.php")
    fun getCategories(): Call<CategoriesResponse>

    @GET("json/v1/1/filter.php")
    fun getMeals(@Query("c") category: String): Call<MealsResponse>

}