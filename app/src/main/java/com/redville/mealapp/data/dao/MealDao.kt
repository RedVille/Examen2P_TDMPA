package com.redville.mealapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.model.User

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

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    @Insert(onConflict = REPLACE)
    fun saveUser(user: User): User

    @Query("SELECT * FROM User WHERE id LIKE :filter")
    fun getUserById(filter: Int): List<User>

    // LIKES

    @Query("SELECT * FROM `Like` WHERE idUser LIKE :filter")
    fun getLikeByIdUser(filter: Int): List<Like>

}