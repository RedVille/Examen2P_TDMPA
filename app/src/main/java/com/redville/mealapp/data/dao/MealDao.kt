package com.redville.mealapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.redville.mealapp.domain.model.Meal

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal WHERE name LIKE :filter")
    fun getMealsByName(filter: String): List<Meal>

    @Insert(onConflict = REPLACE)
    fun saveMeals(meals: List<Meal>): List<Long>

    @Update
    fun updateCocktail(meal: Meal): Int

}