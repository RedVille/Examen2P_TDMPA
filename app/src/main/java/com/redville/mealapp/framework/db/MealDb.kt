package com.redville.mealapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redville.mealapp.data.dao.MealDao
import com.redville.mealapp.domain.model.Category
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.model.User

@Database(entities = [Category::class, Meal::class, User::class, Like::class], version = 1)
abstract class MealDb : RoomDatabase() {

    abstract fun mealDao(): MealDao

}