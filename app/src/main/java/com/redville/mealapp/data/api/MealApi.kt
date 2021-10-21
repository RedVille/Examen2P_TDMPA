package com.redville.mealapp.data.api

import com.redville.mealapp.data.dto.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("json/v1/1/search.php")
    fun getCocktailsByName(@Query("s") name: String): Call<MealsResponse>

}