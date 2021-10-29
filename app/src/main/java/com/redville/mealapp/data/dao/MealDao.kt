package com.redville.mealapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.model.User
import retrofit2.http.DELETE

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

    @Query("SELECT * FROM Meal WHERE idMeal LIKE :filter")
    fun getMealById(filter: Int): List<Meal>

    @Query("SELECT * FROM Meal")
    fun getRandomMeal(): List<Meal>

    @Insert(onConflict = REPLACE)
    fun saveMeals(categories: List<Meal>): List<Long>

    // ACCOUNT

    @Insert(onConflict = REPLACE)
    fun saveUser(user: List<User>): List<Long>

    @Query("SELECT * FROM User WHERE name LIKE :filter")
    fun getUserByName(filter: String): List<User>

    // LIKES

    @Insert(onConflict = REPLACE)
    fun saveLike(like: List<Like>): List<Long>

    @Query("SELECT * FROM `Like` WHERE nameUser LIKE :filter")
    fun getLikeByUser(filter: String): List<Like>

    @Delete
    fun deleteLike(like: List<Like>): Int
}