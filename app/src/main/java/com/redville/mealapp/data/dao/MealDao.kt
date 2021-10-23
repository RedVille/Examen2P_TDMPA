package com.redville.mealapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.model.Meal

@Dao
interface MealDao {

    // CATEGORIES

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Insert(onConflict = REPLACE)
    fun saveCategories(categories: List<Category>): List<Long>

    // MEALS

    @Query("SELECT * FROM Meal WHERE category LIKE :filter")
    fun getMeals(filter: String): List<Meal>

    @Query("SELECT * FROM Meal WHERE name LIKE :filter")
    fun getMealsByName(filter: String): List<Meal>

    @Insert(onConflict = REPLACE)
    fun saveMeals(categories: List<Meal>): List<Long>

}